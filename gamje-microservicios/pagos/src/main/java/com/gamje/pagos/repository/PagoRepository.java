package com.gamje.pagos.repository;

import com.gamje.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByIdUsuario(int idUsuario);
    List<Pago> findByIdCurso(int idCurso);
}