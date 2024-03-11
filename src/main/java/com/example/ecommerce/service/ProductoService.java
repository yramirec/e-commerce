package com.example.ecommerce.service;

import com.example.ecommerce.model.Producto;
import com.example.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Producto> registrarProducto(Producto producto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_ejemplo")
                .declareParameters(
                        new SqlParameter("id", Types.INTEGER),
                        new SqlParameter("nombre", Types.VARCHAR),
                        new SqlParameter("fec_registro", Types.DATE),
                        new SqlOutParameter("codigo", Types.INTEGER),
                        new SqlOutParameter("mensaje", Types.VARCHAR)
                );

        Map<String, Object> params = new HashMap<>();
        params.put("id", producto.getId());
        params.put("nombre", producto.getNombre());
        params.put("fec_registro", producto.getFecRegistro());

        Map<String, Object> result = simpleJdbcCall.execute(params);

        Integer codigo = (Integer) result.get("codigo");
        String mensaje = (String) result.get("mensaje");

        if (codigo != 0) {
            throw new RuntimeException(mensaje);
        }

        return productoRepository.findByFecRegistro(producto.getFecRegistro());
    }
}