package com.curso.ecommerce.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    // /usuario/registro
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        logger.info("Accesos : {}", usuario);

        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());

        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());

            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }
        } else {
            logger.info("Usuario no existe");
        }

        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        logger.info("ordenes {}", ordenes);

        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        logger.info("Id de la orden: {}", id);
        Optional<Orden> orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }

    // Método para generar el PDF
 // Mapeo más explícito
    @GetMapping("/usuario/generar-pdf/{ordenId}")
    public void generarPDF(@PathVariable Integer ordenId, HttpServletResponse response) throws DocumentException, IOException {
        // Obtener la orden desde el servicio
        Optional<Orden> ordenOpt = ordenService.findById(ordenId);
        if (!ordenOpt.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404
            response.getWriter().write("Orden no encontrada");
            return;
        }
        Orden orden = ordenOpt.get();

        // Crear documento PDF
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Agregar contenido al PDF
        document.add(new Paragraph("Detalle de la Orden"));
        document.add(new Paragraph("Nombre: " + orden.getUsuario().getNombre()));
        document.add(new Paragraph("Correo: " + orden.getUsuario().getEmail()));
        document.add(new Paragraph("Dirección: " + orden.getUsuario().getDireccion()));

        document.add(new Paragraph("Productos:"));
        orden.getDetalle().forEach(detalle -> {
            try {
                document.add(new Paragraph("Producto: " + detalle.getNombre()));
                document.add(new Paragraph("Cantidad: " + detalle.getCantidad()));
                document.add(new Paragraph("Precio: " + detalle.getPrecio()));
                document.add(new Paragraph("Total: " + detalle.getTotal()));
                document.add(new Paragraph("-----------------------------------"));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });

        document.add(new Paragraph("Total de la orden: " + orden.getTotal()));
        document.close();

        // Establecer los encabezados para la respuesta de descarga de PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=orden_" + ordenId + ".pdf");

        // Escribir el contenido del PDF al flujo de salida
        response.getOutputStream().write(baos.toByteArray());
        response.getOutputStream().flush();
    }
}

