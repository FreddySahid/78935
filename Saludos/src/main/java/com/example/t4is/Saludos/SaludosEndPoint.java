package com.example.t4is.Saludos;


import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BorrarSaludoRequest;
import https.t4is_uv_mx.saludos.BorrarSaludoResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class SaludosEndPoint {
    int id = 1;
    List<saludos> listaSaludo = new ArrayList<>();

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("hola"+ peticion.getNombre());
        saludos saludar = new saludos();
        saludar.setNombre(peticion.getNombre());
        saludar.setId(id);
        listaSaludo.add(saludar);
        id++;
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarSaludosRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse buscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        for(saludos saludar : listaSaludo){
            BuscarSaludosResponse.Saludos buscarSaludos = new BuscarSaludosResponse.Saludos();
            buscarSaludos.setId(saludar.getId());
            buscarSaludos.setNombre(saludar.getNombre());
            respuesta.getSaludos().add(buscarSaludos);
        }
        return respuesta;

    }

    @PayloadRoot(localPart = "ModificarSaludoResponse", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificaSaludos(@RequestPayload ModificarSaludoRequest peticion){

        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        saludos e = new saludos();
        e.setId(peticion.getId());
        e.setNombre(peticion.getNombre());
        listaSaludo.set(peticion.getId()-1, e);
        respuesta.setRespuesta(true);
        
        
        return respuesta;

    }

    @PayloadRoot(localPart = "BorrarSaludoResponse", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BorrarSaludoResponse borrarSaludos(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        for(saludos o : listaSaludo){
            if(o.getId() == peticion.getId()){
                listaSaludo.remove(o);
                break;
            }
        }

        respuesta.setRespuesta(true);
        return respuesta;

    }

    

    
    
}
