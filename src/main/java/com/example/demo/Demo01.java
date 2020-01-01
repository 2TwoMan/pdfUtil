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
    private static final String filePath = "E:\\test" + System.currentTimeMillis() + ".pdf";
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
        PdfPTable wapper = new PdfPTable(1);
        wapper.setWidthPercentage(100);
        wapper.getDefaultCell().setBorderWidth(10);
        wapper.setSpacingBefore(0);

        PdfPTable table = new PdfPTable(13);
        table.setWidthPercentage(100);
        table.setSpacingBefore(0);

        PdfPCell one = new PdfPCell();
        one.setRowspan(3);
        Image image = Image.getInstance("C:\\Users\\Administrator\\Desktop\\图片1.png");
        image.setAlignment(Image.ALIGN_CENTER);
        one.setImage(image);
        one.setUseAscender(true);
        one.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        one.setHorizontalAlignment(Element.ALIGN_CENTER);
        one.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(one);

        PdfPCell two = new PdfPCell();
        two.setColspan(12);
        two.setPhrase(new Phrase("中国建筑项目管理表格", keyfont));
        two.setUseAscender(true);
        two.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        two.setHorizontalAlignment(Element.ALIGN_CENTER);
        two.setVerticalAlignment(Element.ALIGN_MIDDLE);
        two.setFixedHeight(30);

        table.addCell(two);

        PdfPCell three = new PdfPCell();
        three.setColspan(9);
        three.setRowspan(2);
        three.setPhrase(new Phrase("收料单", keyfont2));
        three.setUseAscender(true);
        three.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        three.setHorizontalAlignment(Element.ALIGN_CENTER);
        three.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(three);

        PdfPCell four = new PdfPCell();
        four.setColspan(3);
        four.setPhrase(new Phrase("表格格式", textfont));
        four.setUseAscender(true);
        four.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        four.setHorizontalAlignment(Element.ALIGN_CENTER);
        four.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(four);

        PdfPCell five = new PdfPCell();
        five.setColspan(3);
        five.setPhrase(new Phrase("CSCEC8B-PS-B30402", textfont));
        five.setUseAscender(true);
        five.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        five.setHorizontalAlignment(Element.ALIGN_CENTER);
        five.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(five);

        PdfPTable table2 = new PdfPTable(new float[]{120, 515, 65, 210});
        table2.setWidthPercentage(100);
        table2.setSpacingBefore(0);
        table2.getDefaultCell().setUseAscender(true);
        table2.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table2.getDefaultCell().setFixedHeight(30);
        table2.addCell(new Phrase("项目名称编码", textfont));
        table2.addCell(new Phrase(" ", textfont));
        table2.addCell(new Phrase("日期", textfont));
        table2.addCell(new Phrase(" ", textfont));

        PdfPCell six = new PdfPCell();
        six.setBorderWidthTop(3f);
        six.setBorderColorTop(new BaseColor(1, 1, 1, 1));
        six.setColspan(13);
        six.addElement(table2);
        six.setPadding(0);

        table.addCell(six);

        PdfPTable table3 = new PdfPTable(new float[]{120, 515, 65, 210});
        table3.setWidthPercentage(100);
        table3.setSpacingBefore(0);
        table3.getDefaultCell().setUseAscender(true);
        table3.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table3.getDefaultCell().setFixedHeight(30);
        table3.addCell(new Phrase("供应单位", textfont));
        table3.addCell(new Phrase(" ", textfont));
        table3.addCell(new Phrase("编号", textfont));
        table3.addCell(new Phrase(" ", textfont));

        PdfPCell severn = new PdfPCell();
        severn.setColspan(13);
        severn.addElement(table3);
        severn.setPadding(0);

        table.addCell(severn);

        // -----------------------------------------

        PdfPTable table4 = new PdfPTable(new float[]{70, 70, 70, 50, 70, 50, 70, 90, 85.5f, 65, 71.5f, 70, 65});
        table4.setWidthPercentage(100);
        table4.setSpacingBefore(0);
        table4.getDefaultCell().setUseAscender(true);
        table4.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table4.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table4.getDefaultCell().setFixedHeight(40);
        table4.addCell(new Phrase("物质名称", textfont2));
        table4.addCell(new Phrase("规格型号", textfont2));
        table4.addCell(new Phrase("计量\n\n单位", textfont2));
        table4.addCell(new Phrase("数量", textfont2));
        table4.addCell(new Phrase("税前单价", textfont2));
        table4.addCell(new Phrase("税率", textfont2));
        table4.addCell(new Phrase("税后单价", textfont2));
        table4.addCell(new Phrase("税前总金额", textfont2));
        table4.addCell(new Phrase("税后总金额", textfont2));
        table4.addCell(new Phrase("厂家\n\n/品牌", textfont2));
        table4.addCell(new Phrase("计算方式", textfont2));
        table4.addCell(new Phrase("使用类别", textfont2));
        table4.addCell(new Phrase("备注", textfont2));

        int length = 130;
        table4.getDefaultCell().setFixedHeight(30);
        for (int i = 0; i < length; i++) {
            table4.addCell(new Phrase(i + "", textfont2));
        }

        PdfPCell eight = new PdfPCell();
        eight.setColspan(13);
        eight.addElement(table4);
        eight.setPadding(0);
        table.addCell(eight);

        PdfPTable table5 = new PdfPTable(new float[]{80, 155, 80, 150, 80, 155, 80, 155});
        table5.setWidthPercentage(100);
        table5.setSpacingBefore(0);
        table5.getDefaultCell().setUseAscender(true);
        table5.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table5.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table5.getDefaultCell().setFixedHeight(40);
        table5.addCell(new Phrase("供应商送\n料人", textfont2));
        table5.addCell(new Phrase(" ", textfont2));
        table5.addCell(new Phrase("值班人员", textfont2));
        table5.addCell(new Phrase(" ", textfont2));
        table5.addCell(new Phrase("收料人", textfont2));
        table5.addCell(new Phrase(" ", textfont2));
        table5.addCell(new Phrase("分包商收\n料人", textfont2));
        table5.addCell(new Phrase("", textfont2));

        PdfPCell nine = new PdfPCell();
        nine.setColspan(13);
        nine.addElement(table5);
        nine.setPadding(0);
        table.addCell(nine);


        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(2.5f);
        cell.setPadding(0);
        cell.addElement(table);
        wapper.addCell(cell);


        document.add(wapper);

    }


    public static PdfPTable createTable(float[] widths, int align, int disable, int curNumber) {
        PdfPTable table = new PdfPTable(widths);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        if (curNumber > 0) {
            for (int i = 0; i < curNumber; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(" "));
                cell.setPadding(0);
                if (i == 0 || i == curNumber - 1) {
                    cell.disableBorderSide(12);
                }
                cell.disableBorderSide(disable);
                table.addCell(cell);
            }
        }
        return table;
    }


    /**
     * 创建指定列宽、列数的表格
     *
     * @param widths
     * @return
     */
    public static PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100);
        // 水平对齐方式
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        return table;
    }

    /**
     * 创建指定列宽、列数的表格
     *
     * @param widths
     * @return
     */
    public static PdfPTable createNoBorderTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        table.setWidthPercentage(100);
        try {
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 创建默认列宽,指定参数，水平（居中、右、左）的表格
     *
     * @param colNumber
     * @param align
     * @return
     */
    public static PdfPTable createTable(int colNumber, int align) {
        PdfPTable table = new PdfPTable(colNumber);
        table.setWidthPercentage(100);
        try {
            table.setHorizontalAlignment(align);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }


    /**
     * 创建单元格(指定字体)
     *
     * @param value
     * @param font
     * @return
     */
    public static PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }


    /**
     * 创建单元格（指定字体、水平..）
     *
     * @param value
     * @param font
     * @param align
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 创建单元格(指定字体,水平,无边框)
     *
     * @param value
     * @param font
     * @return
     */
    public static PdfPCell createCellNoBorder(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setBorder(0);
        return cell;
    }

    /**
     * 创建单元格（指定字体、水平居..、单元格跨x列合并）
     *
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @return
     */
    public static PdfPCell createColspanCell(String value, Font font, int align, int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 创建单元格（指定字体、水平居..、单元格跨x列合并）
     *
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @return
     */
    public static PdfPCell createNoBorderColspanCell(String value, Font font, int align, int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.disableBorderSide(1);
        cell.setPhrase(new Phrase(value, font));
        cell.setBorder(0);
        return cell;
    }

    /**
     * 创建单元格（指定字体、水平居..、单元格跨x行合并）
     *
     * @param value
     * @param font
     * @param align
     * @param rowspan
     * @return
     */
    public static PdfPCell createRowspanCell(String value, Font font, int align, int rowspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 创建单元格（指定字体、水平居..、单元格跨x行合并）
     *
     * @param value
     * @param font
     * @param align
     * @param rowspan
     * @return
     */
    public static PdfPCell createColAndRowspanCell(String value, Font font, int align, int colspan, int rowspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }


    /**
     * 创建单元格（指定字体、水平居..、单元格跨x列合并、设置单元格内边距）
     *
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @param boderFlag
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        } else if (boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(0.0f);
            cell.setPaddingBottom(15.0f);
        }
        return cell;
    }

    /**
     * 创建单元格（指定字体、水平..、边框宽度：0表示无边框、内边距）
     *
     * @param value
     * @param font
     * @param align
     * @param borderWidth
     * @param paddingSize
     * @param flag
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align, float[] borderWidth, float[] paddingSize, boolean flag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setBorderWidthLeft(borderWidth[0]);
        cell.setBorderWidthRight(borderWidth[1]);
        cell.setBorderWidthTop(borderWidth[2]);
        cell.setBorderWidthBottom(borderWidth[3]);
        cell.setPaddingTop(paddingSize[0]);
        cell.setPaddingBottom(paddingSize[1]);
        if (flag) {
            cell.setColspan(2);
        }
        return cell;
    }


    // ------------------------------------------------------

    /**
     * 创建空白的表格
     *
     * @return
     */
    public static PdfPTable createBlankTable() {
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("", keyfont));
        table.setSpacingAfter(20.0f);
        table.setSpacingBefore(20.0f);
        return table;
    }

}
