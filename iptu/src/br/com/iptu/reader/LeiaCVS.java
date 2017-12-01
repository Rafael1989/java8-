package br.com.iptu.reader;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LeiaCVS implements ActionListener{
	private static final String REFERENCIA_IMOVEL = "referenciaImovel";
	private static final String CEP_IMOVEL = "cepImovel";
	private static final String NOME_CONTRIBUINTE = "nomeContribuinte";
	private static final String IPTU = "setorEQuadra";

	public LeiaCVS() {
	}

	public static void main(String[] args) {
		JFrame janela = new JFrame("IPTU 2017");
		janela.setDefaultCloseOperation(3);
		janela.setLayout(new BorderLayout());
		JPanel painelBotoes = new JPanel(new GridLayout());
		janela.add(painelBotoes, "South");
		JButton botaoCarregar = new JButton("Gerar Arquivo");
		botaoCarregar.setMnemonic(67);
		JPanel painelInputs = new JPanel(new GridLayout());
		janela.add(painelInputs, "Center");
		JRadioButton iptu = new JRadioButton("IPTU");
		JRadioButton nomeContribuinte = new JRadioButton("Nome do contribuinte");
		JRadioButton cepImovel = new JRadioButton("Cep do imóvel");
		JRadioButton referenciaImovel = new JRadioButton("Referência do imóvel");
		painelInputs.add(iptu, new GridLayout());
		painelInputs.add(nomeContribuinte, new GridLayout());
		painelInputs.add(cepImovel, new GridLayout());
		painelInputs.add(referenciaImovel, new GridLayout());
		JLabel jLabel = new JLabel("Digite o setor e quadra");
		painelInputs.add(jLabel, "West");
		JTextField jTextField = new JTextField(20);
		painelInputs.add(jTextField, "East");
		JFileChooser chooser = new JFileChooser("C:/Users/Rafael/Desktop");
		int retorno = chooser.showOpenDialog(null);
		LeiaCVS obj = new LeiaCVS();
		if (retorno == 0) {
			botaoCarregar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(iptu.isSelected()) {
						run(jTextField.getText(), chooser.getSelectedFile(), IPTU);
					}
					if(nomeContribuinte.isSelected()) {
						run(jTextField.getText(), chooser.getSelectedFile(), NOME_CONTRIBUINTE);
					}
					if(cepImovel.isSelected()) {
						run(jTextField.getText(), chooser.getSelectedFile(), CEP_IMOVEL);
					}
					if(referenciaImovel.isSelected()) {
						run(jTextField.getText(), chooser.getSelectedFile(), REFERENCIA_IMOVEL);
					}
					
				}
			});
		}

		painelBotoes.add(botaoCarregar);
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}

	public static void run(String filter, File file, String filterSelected) {
		BufferedReader br = null;
		String line = "";
		String csvDivisor = ";";
		try {
			br = new BufferedReader(new FileReader(file));
			PrintWriter printWriter = new PrintWriter(file.getAbsolutePath() + "IPTU2017-" + filter + ".csv");
			StringBuilder stringBuilder = new StringBuilder();
			line = br.readLine();
			String[] register = line.split(csvDivisor);
			for (int i = 0; i < 35; i++) {
				addRegister(stringBuilder, register, i);
			}
			boolean jumpLine = false;
			int contador = 0;
			while ((line = br.readLine()) != null) {
				contador++;
				register = line.split(csvDivisor);
				jumpLine = false;
				for (int i = 0; i < 35; i++) {
					if (filterSelected.equals("setorEQuadra")) {
						if (register[0].contains(filter)) {
							addRegister(stringBuilder, register, i);
							jumpLine = true;
						}
					} else if (filterSelected.equals("nomeContribuinte")) {
						if ((register[6].contains(filter)) || (register[9].contains(filter))) {
							addRegister(stringBuilder, register, i);
							jumpLine = true;
						}
					} else if (filterSelected.equals("cepImovel")) {
						if (register[17].replace("-", "").contains(filter.replace("-", ""))) {
							addRegister(stringBuilder, register, i);
							jumpLine = true;
						}
					} else if ((filterSelected.equals("referenciaImovel")) && (register[16].contains(filter))) {
						addRegister(stringBuilder, register, i);
						jumpLine = true;
					}
				}

				if (jumpLine) {
					stringBuilder.append("\n");
				}
			}
			stringBuilder.append(contador);
			printWriter.write(stringBuilder.toString());
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void addRegister(StringBuilder stringBuilder, String[] pais, int i) {
		stringBuilder.append(pais[i] + ";");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
