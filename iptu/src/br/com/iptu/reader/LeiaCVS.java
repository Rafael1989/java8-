package br.com.iptu.reader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
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
	private static final String CEP_IMOVEL_MAIS_NUMERO = "cepImovelMaisNumero";
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
		JPanel painelInputs = new JPanel();
		painelInputs.setLayout(null);
		janela.add(painelInputs, "Center");
		JLabel jLabel2 = new JLabel("VOCÊ SÓ PODE SELECIONAR UM POR VEZ!!!");
		jLabel2.setForeground(new Color(255, 0, 0));
		jLabel2.setBounds(5, 20, 800, 20);
		JRadioButton iptu = new JRadioButton("IPTU - SELECIONE E DIGITE O SETOR, OU O SETOR E QUADRA");
		iptu.setBounds(5, 40, 800, 20);
		JRadioButton nomeContribuinte = new JRadioButton("Nome do contribuinte - SELECIONE E DIGITE O NOME ");
		nomeContribuinte.setBounds(5, 60, 800, 20);
		JRadioButton cepImovel = new JRadioButton("Cep do imóvel - SELECIONE E DIGITE O CEP COM TRAÇO OU SEM");
		cepImovel.setBounds(5,80,800,20);
		JRadioButton cepImovelMaisNumero = new JRadioButton("Cep mais número - Ex: 04415000,360");
		cepImovelMaisNumero.setBounds(5,100,800,20);
		JRadioButton referenciaImovel = new JRadioButton("Referência do imóvel - SELECIONE E DIGITE O NOME DO EDIFÍCIO");
		referenciaImovel.setBounds(5, 120, 800, 20);
		JLabel jLabel = new JLabel("DIGITE APENAS O QUE SELECIONOU!!!!");
		jLabel.setForeground(new Color(255, 0, 0));
		jLabel.setBounds(5, 140, 300, 20);
		JTextField jTextField = new JTextField(20);
		jTextField.setBounds(5, 160, 300, 20);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(iptu);
		buttonGroup.add(nomeContribuinte);
		buttonGroup.add(cepImovel);
		buttonGroup.add(cepImovelMaisNumero);
		buttonGroup.add(referenciaImovel);
		painelInputs.add(jLabel2);
		painelInputs.add(iptu);
		painelInputs.add(nomeContribuinte);
		painelInputs.add(cepImovel);
		painelInputs.add(cepImovelMaisNumero);
		painelInputs.add(referenciaImovel);
		painelInputs.add(jLabel);
		painelInputs.add(jTextField);
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
					if(cepImovelMaisNumero.isSelected()) {
						run(jTextField.getText(), chooser.getSelectedFile(), CEP_IMOVEL_MAIS_NUMERO);
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
			filter = filter.trim();
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
					if (filterSelected.equals(IPTU)) {
						if (register[0].startsWith(filter)) {
							addRegister(stringBuilder, register, i);
							jumpLine = true;
						}
					} else if (filterSelected.equals(NOME_CONTRIBUINTE)) {
						if ((register[6].contains(filter)) || (register[9].contains(filter))) {
							addRegister(stringBuilder, register, i);
							jumpLine = true;
						}
					} else if (filterSelected.equals(CEP_IMOVEL)) {
						if (register[17].replace("-", "").contains(filter.replace("-", ""))) {
							addRegister(stringBuilder, register, i);
							jumpLine = true;
						}
					} else if(filterSelected.equals(CEP_IMOVEL_MAIS_NUMERO)) {
						String[] split = filter.split(",");
						if (register[17].replace("-", "").contains(split[0].replace("-", ""))) {
							if (register[13].contains(split[1])) {
								addRegister(stringBuilder, register, i);
								jumpLine = true;
							}
						}
					}else if ((filterSelected.equals(REFERENCIA_IMOVEL)) && (register[16].contains(filter))) {
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
