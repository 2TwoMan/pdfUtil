package com.example.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: CL
 * @Date: 2019/12/31 17:59
 */
public class Demo01 {
    private static final String filePath = "E:\\test" + System.currentTimeMillis() + ".pdf";
    private static Font keyfont;
    private static Font textfont;

    static {
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            keyfont = new Font(bfChinese, 21, Font.BOLD);
            textfont = new Font(bfChinese, 11, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        //横向
        Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth());
        pageSize.rotate();
        document.setPageSize(pageSize);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        generatePDF(document);
        document.close();
    }

    /**
     * 生成PDF文件
     *
     * @param document
     */
    public static void generatePDF(Document document) throws DocumentException {
        // 表格
        PdfPTable table = createTable(new float[]{110f, 110f, 110f, 80f, 110f, 80f, 110f, 110f, 110f, 110f, 110f, 110f, 80f});
        // 第一行
        table.addCell(createRowspanCell(" ", keyfont, Element.ALIGN_BASELINE, 3));
        table.addCell(createColspanCell("中国建筑项目管理表格", keyfont, Element.ALIGN_CENTER, 12));
        // 第二行
        table.addCell(createColAndRowspanCell("收料单", textfont, Element.ALIGN_CENTER, 9, 2));
        table.addCell(createColspanCell("表格编号", textfont, Element.ALIGN_CENTER, 3));
        // 第三行
        table.addCell(createColspanCell("CSCEC8B-PS-B30402", textfont, Element.ALIGN_CENTER, 3));
        // 第四行
        table.addCell(createColspanCell("项目名称及编码", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createColspanCell(" ", textfont, Element.ALIGN_CENTER, 7));
        table.addCell(createCell("日期", textfont, Element.ALIGN_CENTER));
        table.addCell(createColspanCell(" ", textfont, Element.ALIGN_CENTER, 3));
        // 第五行
        table.addCell(createColspanCell("供应单位", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createColspanCell(" ", textfont, Element.ALIGN_CENTER, 7));
        table.addCell(createCell("编号", textfont, Element.ALIGN_CENTER));
        table.addCell(createColspanCell(" ", textfont, Element.ALIGN_CENTER, 3));
        // 第六行
        table.addCell(createRowspanCell("物质名称", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("规格型号", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createCellNoBorder("计量", textfont, Element.ALIGN_CENTER));
        table.addCell(createRowspanCell("数量", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("税前单价", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("税率", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("税后单价", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("税前总金额", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("税后总金额", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createCellNoBorder("厂家", textfont, Element.ALIGN_CENTER));
        table.addCell(createRowspanCell("计算方式", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("使用类别", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createRowspanCell("备注", textfont, Element.ALIGN_CENTER, 2));

        // 第七行
        table.addCell(createCellNoBorder("单位", textfont, Element.ALIGN_CENTER));
        table.addCell(createCellNoBorder("/品牌", textfont, Element.ALIGN_CENTER));

        // 内容
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                table.addCell(createCell(" ", textfont));
            }
        }

        // 第十八行
        table.addCell(createCell("专业工程师", textfont, Element.ALIGN_CENTER));
        table.addCell(createColAndRowspanCell(" ", textfont, Element.ALIGN_CENTER, 2, 1));
        table.addCell(createCell("材料负责人", textfont, Element.ALIGN_CENTER));
        table.addCell(createColAndRowspanCell(" ", textfont, Element.ALIGN_CENTER, 2, 1));
        table.addCell(createRowspanCell("收料人", textfont, Element.ALIGN_CENTER, 2));
        table.addCell(createColAndRowspanCell(" ", textfont, Element.ALIGN_CENTER, 2, 1));
        table.addCell(createCell("供应商送料人", textfont, Element.ALIGN_CENTER));
        table.addCell(createColAndRowspanCell(" ", textfont, Element.ALIGN_CENTER, 3, 1));

//        // 第十九行
//        table.addCell(createCellNoBorder("", textfont, Element.ALIGN_CENTER));
//        table.addCell(createCellNoBorder("", textfont, Element.ALIGN_CENTER));
//        table.addCell(createCellNoBorder("", textfont, Element.ALIGN_CENTER));

        document.add(table);
    }

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

    /**
     * 创建指定列宽、列数的表格
     *
     * @param widths
     * @return
     */
    public static PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100);
        try {
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
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

}
