package com.pluralsight.foodCourt;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.pluralsight.foodCourt.CartService;
import com.pluralsight.foodCourt.OrderItem;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
public class ReceiptController {

    private final CartService cartService;

    public ReceiptController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/receipt")
    public void generateReceipt(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=receipt.pdf");

        try {
            Document document = new Document(PageSize.A5); // smaller, boutique-style receipt
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Luxury Fonts
            Font brandFont = new Font(Font.TIMES_ROMAN, 26, Font.BOLD);
            Font headerFont = new Font(Font.TIMES_ROMAN, 14, Font.NORMAL);
            Font bodyFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
            Font totalFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);

            // Monogram / Brand Title (Centered)
            Paragraph brand = new Paragraph("PRINCE'S FOOD COURT", brandFont);
            brand.setAlignment(Element.ALIGN_CENTER);
            document.add(brand);

            Paragraph sub = new Paragraph("Fine Modern Dining", headerFont);
            sub.setAlignment(Element.ALIGN_CENTER);
            document.add(sub);

            document.add(new Paragraph("\n")); // whitespace

            // Thin luxury divider
            document.add(new Paragraph("────────────────────────────────────"));

            // Time + Order Number
            document.add(new Paragraph("Order #: " + System.currentTimeMillis(), bodyFont));
            document.add(new Paragraph("Date: " + java.time.LocalDate.now(), bodyFont));
            document.add(new Paragraph("Time: " + java.time.LocalTime.now().withNano(0), bodyFont));

            document.add(new Paragraph("────────────────────────────────────\n"));

            // Item Table
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3.5f, 1.5f});

            for (OrderItem item : cartService.getItems()) {
                PdfPCell itemCell = new PdfPCell(new Phrase(item.getDisplayName(), bodyFont));
                PdfPCell priceCell = new PdfPCell(new Phrase(String.format("$%.2f", item.getCost()), bodyFont));
                itemCell.setBorder(Rectangle.NO_BORDER);
                priceCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(itemCell);
                table.addCell(priceCell);
            }

            document.add(table);

            document.add(new Paragraph("\n────────────────────────────────────\n"));

            // Total aligned right
            Paragraph total = new Paragraph("Total: $" + String.format("%.2f", cartService.getTotal()), totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.add(new Paragraph("\n\nThank you for dining with us.", bodyFont));
            document.add(new Paragraph("We appreciate your refined taste.", new Font(Font.TIMES_ROMAN, 12, Font.ITALIC)));

            document.add(new Paragraph("\n"));
            Paragraph sig = new Paragraph("— Prince", new Font(Font.TIMES_ROMAN, 14, Font.BOLDITALIC));
            sig.setAlignment(Element.ALIGN_RIGHT);
            document.add(sig);

            document.close();
            cartService.clear();

        } catch (DocumentException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating PDF receipt.");
        }
    }


}

