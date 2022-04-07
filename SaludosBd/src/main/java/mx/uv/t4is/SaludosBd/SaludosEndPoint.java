package mx.uv.t4is.SaludosBd;


// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    /*int id = 1;
    List<saludos> listaSaludo = new ArrayList<>();*/
    @Autowired
    Isaludadores isaludadores;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("hola"+ peticion.getNombre());
        Saludos saludar = new Saludos();
        saludar.setNombre(peticion.getNombre());
        //saludar.setId(id);
        //listaSaludo.add(saludar);
        //id++;
        isaludadores.save(saludar);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarSaludosRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse BuscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        Iterable<Saludos> lista = isaludadores.findAll();
        for (Saludos o : lista) {
            BuscarSaludosResponse.Saludos saludosBuscar = new BuscarSaludosResponse.Saludos();
            saludosBuscar.setId(o.getId());
            saludosBuscar.setNombre(o.getNombre());
            respuesta.getSaludos().add(saludosBuscar);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarSaludoRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludos e = new Saludos();
        e.setId(peticion.getId());
        e.setNombre(peticion.getNombre());
        //saludos.set(peticion.getId()-1, e);
        isaludadores.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarSaludoRequest",namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BorrarSaludoResponse borrarSaludo(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        //saludos.remove(peticion.getId()-1);
        /*for (Saludos o : saludos){
            if (o.getId() == peticion.getId()){
                saludos.remove(o);
                break;
            }
        }*/
        isaludadores.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }

    

    
    
}
