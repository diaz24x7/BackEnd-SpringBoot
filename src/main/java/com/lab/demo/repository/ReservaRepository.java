package com.lab.demo.repository;

import com.lab.demo.model.Reserva;
import com.lab.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByIdUsuario(Integer id_usuario);
    List<Reserva> findByEstado(String estado);

    @Query(value = " SELECT  res.*,usu.*,rol.*\n" +
            "\n" +
            "FROM public.reserva as res\n" +
            "\n" +
            "INNER JOIN public.usuario as usu\n" +
            "ON res.id_usuario = usu.id_usuario \n" +
            "\n" +
            "INNER JOIN public.rol as rol\n" +
            "ON usu.id_rol = rol.id_rol\n" +
            "\n" +
            "ORDER BY res.fecha_reserva\n", nativeQuery = true)
    List<Map<Object,String>> getGestiónReserva ();
}
