package ru.gb.anfilofyev;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Milk"));
        this.productRepository.insert(new Product("Bread"));
        this.productRepository.insert(new Product("Butter"));
        this.productRepository.insert(new Product("Cocoa powder"));
        this.productRepository.insert(new Product("Sugar"));
        this.productRepository.insert(new Product("Coffee"));
        this.productRepository.insert(new Product("More Coffee"));
        this.productRepository.insert(new Product("Doshirak"));
        this.productRepository.insert(new Product("Apple"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>title</th>");
        writer.println("</tr>");

        for (Product product : productRepository.findAll()) {
            writer.println("<tr>");
            writer.println("<td>" + product.getId() + "</td>");
            writer.println("<td>" + product.getTitle() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
    }
}
