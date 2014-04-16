package printingInvoice;

import java.awt.print.*;
import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

import records.Bill;
import records.Record;
import vips.Vip;

public class Printing extends JPanel implements Printable {
	private Bill bill;
	private List<Record> records;
	private Vip vip;
	
	public Printing(Bill bill, List<Record> records, Vip vip){
		this.bill = bill;
		this.records = records;
		this.vip = vip;
	}

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}
		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		int paperWidth = 132;
		int height = 20;
		String vipID = "----";
		if (vip != null){
			vipID = vip.getVipID() + "";
		}
		Font font = new Font("TimesRoman", Font.PLAIN, 12);
		g.setFont(font);
		String shopTitle = "NANNA INN";
		int shopTitleLen = (int) g.getFontMetrics().getStringBounds(shopTitle, g).getWidth();
		g.drawString(shopTitle, paperWidth / 2 - shopTitleLen / 2, height);
		height += 20;
		font = new Font("TimesRoman", Font.PLAIN, 8);
		font = new Font("TimesRoman", Font.PLAIN, 8);
		g.setFont(font);
		shopTitle = "Shop 203, 2/F, Phase 1 Metro City";
		shopTitleLen = (int) g.getFontMetrics().getStringBounds(shopTitle, g).getWidth();
		g.drawString(shopTitle, paperWidth / 2 - shopTitleLen / 2, height);
		height += 15;
		shopTitle = "Tseung Kwan O, NT";
		shopTitleLen = (int) g.getFontMetrics().getStringBounds(shopTitle, g).getWidth();
		g.drawString(shopTitle, paperWidth / 2 - shopTitleLen / 2, height);
		height += 15;
		shopTitle = "Tel : 27020777";
		shopTitleLen = (int) g.getFontMetrics().getStringBounds(shopTitle, g).getWidth();
		g.drawString(shopTitle, paperWidth / 2 - shopTitleLen / 2, height);
		height += 15;
		font = new Font("TimesRoman", Font.PLAIN, 12);
		String s = "銷售單";
		int stringLen = (int) g.getFontMetrics().getStringBounds(s, g)
				.getWidth();
		int start = paperWidth / 2 - stringLen / 2;
		g.drawString("銷售單", start, height);
		font = new Font("TimesRoman", Font.PLAIN, 9);
		g.setFont(font);
		height += 15;
		g.drawString("單號：", 0, height);		g.drawString(bill.getBillID() + "", start, height);
		height += 15;
		g.drawString("交易日期：", 0, height);	g.drawString(bill.getDate() + "", start, height);
		height += 15;
		g.drawString("客戶編號：", 0, height);	g.drawString(vipID, start, height); 
		s = "售價";
		stringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		height += 20;
		g.drawString("貨品編號", 0, height);
		g.drawString("銷售編號", start + 15, height);
		g.drawString("售價", paperWidth - stringLen, height);
		height += 3;
		g.drawLine(0, height, paperWidth, height);
		height += 2;
		for (Record record : records){
			height += 10;
			Double retailPrice = record.getRetailPrice();
			g.drawString(record.getProductID(), 0, height);
			g.drawString(record.getRecordID() + "", start + 15, height);
			g.drawString(retailPrice.toString(), paperWidth - stringLen - 10, height);
		}
		height += 5;
		g.drawLine(0, height, paperWidth, height);
		height += 10;
		g.drawString("合計： ＨＫＤ", 0, height); g.drawString(bill.getTotal().toString(), paperWidth - stringLen - 10, height);
		height += 15;
		s = "多謝惠顧";
		stringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		start = paperWidth / 2 - stringLen / 2;
		g.drawString("多謝惠顧", start, height);
		return PAGE_EXISTS;
	}
}