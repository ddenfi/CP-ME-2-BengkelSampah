package com.bengkelsampah.bengkelsampahapp.ui.history

import android.content.Context
import com.bengkelsampah.bengkelsampahapp.R
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteBoxModel
import com.bengkelsampah.bengkelsampahapp.domain.model.WasteOrderModel
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.Border
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import com.itextpdf.layout.property.VerticalAlignment
import java.io.File
import java.io.FileOutputStream

class HistoryDetailPdfFile {
    fun generatePdfFile(context: Context, history: WasteOrderModel) {
        val fileDir = File(context.getExternalFilesDir(null), "Documents")
        if (!fileDir.exists()) fileDir.mkdirs()

        val fileNameOutput = "$fileDir/${history.id}.pdf"
        val pdfWriter = PdfWriter(FileOutputStream(fileNameOutput))
        val doc = Document(PdfDocument(pdfWriter), PageSize.A4, true)

        pdfHeader(doc, context, history)
        doc.add(Paragraph("\n"))
        pdfWasteSold(doc, context, history)

        doc.close()
    }

    private fun pdfWasteSold(doc: Document, context: Context, history: WasteOrderModel) {
        doc.add(
            Paragraph(context.getString(R.string.waste_type))
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
        )

        val wasteSoldTable = Table(UnitValue.createPercentArray(floatArrayOf(7f, 3f)))
            .setHorizontalAlignment(HorizontalAlignment.CENTER)
            .setWidth(UnitValue.createPercentValue(50f))

        for (waste in history.wasteBox) {
            val wasteName = waste.waste.name
            val wasteAmount = waste.amount
            val wasteUnit = waste.waste.unit
            val wastePricePerUnit = waste.waste.pricePerUnit

            wasteSoldTable
                .addCell(
                    Cell()
                        .add(Paragraph(wasteName).setBold())
                        .add(Paragraph("$wasteAmount $wasteUnit x $wastePricePerUnit"))
                        .setBorder(Border.NO_BORDER)
                )
                .addCell(
                    Cell()
                        .add(
                            Paragraph(
                                WasteBoxModel.countSubtotal(wastePricePerUnit, wasteAmount)
                                    .toString()
                            )
                        )
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setBorder(Border.NO_BORDER)
                        .setVerticalAlignment(VerticalAlignment.BOTTOM)
                )
        }

        wasteSoldTable
            .addCell(
                Cell(1, 2)
                    .add(
                        Paragraph(
                            context.getString(
                                R.string.total_detail_history,
                                history.total.toString()
                            )
                        )
                            .setBold()
                            .setTextAlignment(TextAlignment.RIGHT)
                    )
                    .setBorderLeft(Border.NO_BORDER)
                    .setBorderRight(Border.NO_BORDER)
                    .setBorderBottom(Border.NO_BORDER)
            )

        doc.add(wasteSoldTable)
    }

    private fun pdfHeader(doc: Document, context: Context, history: WasteOrderModel) {
        val agentName = Paragraph(history.agent).setBold()
        val agentAddress = Paragraph(history.agentAddress)
        val agentPhone = Paragraph(context.getString(R.string.agent_phone, history.agentPhone))

        val pickUpAddressText = Paragraph(context.getString(R.string.pick_up_address))
        val pickUpAddressValue = Paragraph(history.address)

        doc.add(
            Table(UnitValue.createPercentArray(floatArrayOf(5f, 5f))).useAllAvailableWidth()
                .addCell(
                    Cell()
                        .add(agentName)
                        .add(agentAddress)
                        .add(agentPhone)
                        .setBorder(Border.NO_BORDER)
                )
                .addCell(
                    Cell()
                        .add(pickUpAddressText.setTextAlignment(TextAlignment.RIGHT))
                        .add(pickUpAddressValue.setTextAlignment(TextAlignment.RIGHT))
                        .setBorder(Border.NO_BORDER)
                )
        )
    }
}