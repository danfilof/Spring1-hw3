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
        this.productRepository.insert(new Product("Milk", 1.2f));
        this.productRepository.insert(new Product("Bread", 0.3f));
        this.productRepository.insert(new Product("Butter", 1.5f));
        this.productRepository.insert(new Product("Cocoa powder", 0.9f));
        this.productRepository.insert(new Product("Sugar", 0.2f));
        this.productRepository.insert(new Product("Coffee", 0.6f));
        this.productRepository.insert(new Product("More Coffee", 0.6f));
        this.productRepository.insert(new Product("Doshirak", 9999f));
        this.productRepository.insert(new Product("Apple", 0.15f));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>title</th>");
        writer.println("<th>cost</th>");
        writer.println("</tr>");

        for (Product product : productRepository.findAll()) {
            writer.println("<tr>");
            writer.println("<td>" + product.getId() + "</td>");
            writer.println("<td>" + product.getTitle() + "</td>");
            writer.println("<td>" + product.getCost() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
    }
}