package relatorio;

import java.io.IOException;
import java.util.HashMap;

import model.dao.Banco;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class Relatorio {
	public static void listaPedidos()
	{
		String printFileName;
        try {
            String currentPath = "C:\\Users\\Gabriel\\Downloads\\Projeto-FoodTruck-main\\foodtruck";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            JasperRunManager.runReportToPdfFile(currentPath + "/relatorio011.jasper", currentPath + "/report1.pdf", null, Banco.getConnection());
            System.out.println("Relatorio gerado em " + currentPath + "/report1.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
        }
	}
	
	public static void acompanhamentoPedido(int id)
	{
		String printFileName2;
        try {
            String currentPath = "C:\\Users\\Gabriel\\Downloads\\Projeto-FoodTruck-main\\foodtruck";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("idvenda", id);
            JasperRunManager.runReportToPdfFile(currentPath + "/report2.jasper", currentPath + "/report2.pdf", parameters, Banco.getConnection());
            System.out.println("Relatorio 2 gerado em " + currentPath + "/report2.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir 2, por favor verifique o modelo de impressão");
        }
	}
}
