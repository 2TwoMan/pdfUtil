package com.example.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Cell;
import org.springframework.context.Phased;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Phaser;

/**
 * @Author: CL
 * @Date: 2019/12/31 17:59
 */
public class Demo01 {
    private static final String filePath = "C:\\Users\\Administrator\\Desktop\\test" + System.currentTimeMillis() + ".pdf";
    private static Font keyfont;
    private static Font keyfont2;
    private static Font textfont;
    private static Font textfont2;

    static {
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            keyfont = new Font(bfChinese, 15, Font.BOLD);
            keyfont2 = new Font(bfChinese, 15, Font.NORMAL);
            textfont = new Font(bfChinese, 10, Font.NORMAL);
            textfont2 = new Font(bfChinese, (float) 10.5, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        //横向
        Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
        pageSize.rotate();
        document.setPageSize(pageSize);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        acceptStuffOrder(document);
        document.close();
    }

    /**
     * 收料单
     *
     * @param document
     */
    public static void acceptStuffOrder(Document document) throws DocumentException, IOException {
        String imgPath = "C:\\Users\\Administrator\\Desktop\\图片1.png";

        PdfPTable table = createTable(true, 13, 0.5f, 0);

        PdfPCell one = createCell(true, imgPath, "img", keyfont, 0, 3, 0, 0);
        PdfPCell two = createCell(true, "中国建筑项目管理表格", "", keyfont, 12, 1, 0, 30);
        PdfPCell three = createCell(true, "收料单", "", keyfont2, 9, 2, 0, 0);
        PdfPCell four = createCell(true, "表格格式", "", textfont, 3, 1, 0, 15);
        PdfPCell five = createCell(true, "CSCEC8B-PS-B30402", "", textfont, 3, 1, 0, 0);

        float[] colspan4 = {120, 515, 65, 210};
        PdfPTable table2 = createTable(true, colspan4, 0.5f, 30);
        String[] text = {"项目名称编码", " ", "日期", " "};
        tableAddCell(table2, text, textfont2);
        PdfPCell six = createCell(table2, 0.5f);

        colspan4 = new float[]{120, 515, 65, 210};
        PdfPTable table3 = createTable(true, colspan4, 0.5f, 30);
        text = new String[]{"供应单位", " ", "编号", ""};
        tableAddCell(table3, text, textfont);
        PdfPCell severn = createCell(table3, 0.5f);

        float[] colspan13 = {70, 70, 70, 50, 70, 50, 70, 90, 85.5f, 65, 71.5f, 70, 65};
        PdfPTable table4 = createTable(true, colspan13, 0.5f, 40);
        text = new String[]{
                "物质名称", "规格型号", "计量\n\n单位", "数量",
                "税前单价", "税率", "税后单价", "税前总金额",
                "税后总金额", "厂家\n\n/品牌", "计算方式",
                "使用类别", "备注"};
        tableAddCell(table4, text, textfont2);
        int length = 130;
        String[] data = new String[130];
        table4.getDefaultCell().setFixedHeight(30);
        for (int i = 0; i < length; i++) {
            data[i] = String.valueOf(i);
        }
        tableAddCell(table4, data, textfont2);
        PdfPCell eight = createCell(table4, 0.5f);

        float[] colspan8 = {80, 155, 80, 150, 80, 155, 80, 155};
        PdfPTable table5 = createTable(true, colspan8, 0.5f, 40);
        text = new String[]{"供应商送\n料人", " ", "值班人员", " ", "收料人", " ", "分包商收\n料人", " "};
        tableAddCell(table5, text, textfont2);
        PdfPCell nine = createCell(table5, 0.5f);

        PdfPTable wrapper = createTable(true, 1, 10, 0);
        PdfPCell cell = createCell(table, 2.5f);

        table.addCell(one);
        table.addCell(two);
        table.addCell(three);
        table.addCell(four);
        table.addCell(five);
        table.addCell(six);
        table.addCell(severn);
        table.addCell(eight);
        table.addCell(nine);
        wrapper.addCell(cell);
        document.add(wrapper);
    }


    public static PdfPTable createTable(boolean align, int numColumns, float borderWidth, int fixedHeight) {
        PdfPTable table = new PdfPTable(numColumns);
        return tableCommonMethod(align, table, borderWidth, fixedHeight);
    }

    public static PdfPTable createTable(boolean align, float[] widths, float borderWidth, int fixedHeight) {
        PdfPTable table = new PdfPTable(widths);
        return tableCommonMethod(align, table, borderWidth, fixedHeight);
    }

    private static PdfPTable tableCommonMethod(boolean align, PdfPTable table, float borderWidth, int fixedHeight) {
        if (align) {
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
        }
        if (fixedHeight > 0) {
            table.getDefaultCell().setFixedHeight(fixedHeight);
        }
        table.getDefaultCell().setBorderWidth(borderWidth);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.setWidthPercentage(100);
        table.setSpacingBefore(0);
        return table;
    }

    private static PdfPTable tableAddCell(PdfPTable table, String[] text, Font font) {
        for (int i = 0; i < text.length; i++) {
            table.addCell(new Phrase(text[i], font));
        }
        return table;
    }

    // 用于嵌套表
    public static PdfPCell createCell(PdfPTable table, float borderWidth) {
        PdfPCell cell = new PdfPCell();
        cell.setColspan(13);
        cell.setBorderWidth(borderWidth);
        cell.addElement(table);
        cell.setPadding(0);
        return cell;
    }

    public static PdfPCell createCell(boolean align, String value, String type, Font font, int colspan, int rowspan, int padding, int fixedHeight) {
        PdfPCell cell = new PdfPCell();
        // 水平垂直居中
        if (align) {
            cell.setUseAscender(true);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        }
        // 设置单元格内容
        String img = "img";
        if (img.equals(type)) {
            try {
                Image image = Image.getInstance(value);
                image.setAlignment(Image.ALIGN_CENTER);
                cell.setImage(image);
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cell.setPhrase(new Phrase(value, font));
        }
        // 固定高度
        if (fixedHeight > 0) {
            cell.setFixedHeight(fixedHeight);
        }
        cell.setBorderWidth(0.5f);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPadding(padding);
        return cell;
    }
}
