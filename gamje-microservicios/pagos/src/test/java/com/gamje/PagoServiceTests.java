package com.gamje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gamje.pagos.model.Pago;
import com.gamje.pagos.service.PagoService;

    @SpringBootTest
    class PagoServiceTests {
    
        @Autowired
        private PagoService pagoservice;

        @Test
        void probarRegistrarPago()throws Exception{
        Pago pago = new Pago();
        pago.setId(1);
        pago.setIdUsuario(2);
        pago.setIdCurso(1);
        pago.setMonto(50000);
        pago.setFecha(null);

        Pago guardado = pagoservice.registrarPago(pago);

        assertNotNull(guardado.getId());

        assertEquals(2, guardado.getIdUsuario());
        assertEquals(1, guardado.getIdCurso());
        assertEquals(50000, guardado.getMonto());
        assertNotNull(guardado.getFecha());
        assertNotNull(guardado.getFecha());

        }
    
}
