package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ExtratorXml {

	private static final String caminhoXmlDados = "xml\\EMPETUR_PontosTuristicosPE_dado_v2.xml";
	private static final String TAG_DATA = "data";
	private static final String TAG_DATA_CRIACAO = "criacao";
	private static final String TAG_DATA_PUBLICACAO = "publicacao";
	private static final String TAG_ABRANGENCIA = "abrangencia";
	private static final String TAG_ABRANGENCIA_TEMPORAL = "temporal";
	private static final String TAG_REGISTRO = "registro";
	private static final String TAG_CAMPO2 = "campo2";
	private static final String TAG_CAMPO4 = "campo4";
	private static final String TAG_CAMPO5 = "campo5";
	private static final String TAG_CAMPO6 = "campo6";
	private static final String TAG_CAMPO7 = "campo7";
	private static final String TAG_CAMPO8 = "campo8";
	private static final String TAG_CAMPO9 = "campo9";
	private static final String TAG_CAMPO10 = "campo10";
	private static final String TAG_CAMPO11 = "campo11";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String[] MUNICIPIOS_PERNAMBUCANOS = { "", "Abreu e Lima", "Afogados da Ingazeira", "Afrânio",
			"Agrestina", "Água Preta", "Águas Belas", "Alagoinha", "Aliança", "Altinho", "Amaraji", "Angelim",
			"Araçoiaba", "Araripina", "Arcoverde", "Barra de Guabiraba", "Barreiros", "Belém de Maria",
			"Belém do São Francisco", "Belo Jardim", "Betânia", "Bezerros", "Bodocó", "Bom Conselho", "Bom Jardim",
			"Bonito", "Brejão", "Brejinho", "Brejo da Madre de Deus", "Buenos Aires", "Buíque",
			"Cabo de Santo Agostinho", "Cabrobó", "Cachoeirinha", "Caetés", "Calçado", "Calumbi", "Camaragibe",
			"Camocim de São Félix", "Camutanga", "Canhotinho", "Capoeiras", "Carnaíba", "Carnaubeira da Penha",
			"Carpina", "Caruaru", "Casinhas", "Catende", "Cedro", "Chã de Alegria", "Chã Grande", "Condado",
			"Correntes", "Cortês", "Cumaru", "Cupira", "Custódia", "Dormentes", "Escada", "Exu", "Feira Nova",
			"Fernando de Noronha", "Ferreiros", "Flores", "Floresta", "Frei Miguelinho", "Gameleira", "Garanhuns",
			"Glória do Goitá", "Goiana", "Granito", "Gravatá", "Iati", "Ibimirim", "Ibirajuba", "Igarassu", "Iguaracy",
			"Ilha de Itamaracá", "Inajá", "Ingazeira", "Ipojuca", "Ipubi", "Itacuruba", "Itaíba", "Itambé", "Itapetim",
			"Itapissuma", "Itaquitinga", "Jaboatão dos Guararapes", "Jaqueira", "Jataúba", "Jatobá", "João Alfredo",
			"Joaquim Nabuco", "Jucati", "Jupi", "Jurema", "Lagoa de Itaenga", "Lagoa do Carro", "Lagoa do Ouro",
			"Lagoa dos Gatos", "Lagoa Grande", "Lajedo", "Limoeiro", "Macaparana", "Machados", "Manari", "Maraial",
			"Mirandiba", "Moreilândia", "Moreno", "Nazaré da Mata", "Olinda", "Orobó", "Orocó", "Ouricuri", "Palmares",
			"Palmeirina", "Panelas", "Paranatama", "Parnamirim", "Passira", "Paudalho", "Paulista", "Pedra",
			"Pesqueira", "Petrolândia", "Petrolina", "Poção", "Pombos", "Primavera", "Quipapá", "Quixaba", "Recife",
			"Riacho das Almas", "Ribeirão", "Rio Formoso", "Sairé", "Salgadinho", "Salgueiro", "Saloá", "Sanharó",
			"Santa Cruz", "Santa Cruz da Baixa Verde", "Santa Cruz do Capibaribe", "Santa Filomena",
			"Santa Maria da Boa Vista", "Santa Maria do Cambucá", "Santa Terezinha", "São Benedito do Sul",
			"São Bento do Una", "São Caitano", "São João", "São Joaquim do Monte", "São José da Coroa Grande",
			"São José do Belmonte", "São José do Egito", "São Lourenço da Mata", "São Vicente Ferrer", "Serra Talhada",
			"Serrita", "Sertânia", "Sirinhaém", "Solidão", "Surubim", "Tabira", "Tacaimbó", "Tacaratu", "Tamandaré",
			"Taquaritinga do Norte", "Terezinha", "Terra Nova", "Timbaúba", "Toritama", "Tracunhaém", "Trindade",
			"Triunfo", "Tupanatinga", "Tuparetama", "Venturosa", "Verdejante", "Vertente do Lério", "Vertentes",
			"Vicência", "Vitória de Santo Antão", "Xexéu" };
	private static final String[] CATEGORIAS = { "", "Agremiação carnavalesca", "Artesanato", "Atrativos naturais",
			"Bares e cachaçarias", "Câmbio", "Casa noturna", "Cinemas e teatros", "Companhias aéreas", "Consulado",
			"Esportes", "Eventos", "Hospedagem", "Hospital", "Locadora de veículo", "Mercados públicos e feiras livres",
			"Museus, centros e patrimônios culturais", "Passeios náuticos", "Praças e parques", "Religião",
			"Restaurante", "Shopping Center", "Trilha", "Turismo" };

	public static String iniciarExtrator() {
		return ExtratorXml.extratorXmlParaSql();
	}

	private static String imprimirLinha() {
		return "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
	}

	private static String criaCabecalhoSql(String dataCriacao, String dataPublicacao, String dataAbrangenciaInicio,
			String dataAbrangenciaFim, String dataHoje) {
		String cabecalhoSql = "";

		// Preenchendo retorno com as datas de criação, publicação e abrangência
		// dos dados
		cabecalhoSql = "----------------------------------------\n" + "---------------- DATAS -----------------\n"
				+ "-- CRIAÇÃO : " + dataCriacao + " ----------------\n" + "-- PUBLICAÇÃO : " + dataPublicacao
				+ " -------------\n" + "-- ABRANGÊNCIA (INÍCIO): " + dataAbrangenciaInicio + " ----\n"
				+ "-- ABRANGÊNCIA (FIM): " + dataAbrangenciaFim + " -------\n" + "-- DATA EXTRAÇÃO: " + dataHoje
				+ " --\n" + "----------------------------------------\n" + "----------------------------------------\n";

		// Criando a database se não existe
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "USE j93wnosow6net7ja;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE IF NOT EXISTS MUNICIPIO (\n"
				+ "	ID_MUNICIPIO INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" + "	MUNICIPIO VARCHAR(255)\n" + ");\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE IF NOT EXISTS CATEGORIA (\n"
				+ "	ID_CATEGORIA INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" + "	CATEGORIA VARCHAR(255)\n" + ");\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE IF NOT EXISTS PONTO_TURISTICO (\n"
				+ "	ID_PONTO_TURISTICO INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" + "	NOME VARCHAR(255) NOT NULL,\n"
				+ "	LATITUDE VARCHAR(255),\n" + "	LONGITUDE VARCHAR(255),\n" + "	ALTITUDE VARCHAR(255),\n"
				+ "	ID_CATEGORIA INT,\n" + "	DESCRICAO LONGTEXT,\n" + "	IDIOMA VARCHAR(255),\n"
				+ "	LOGRADOURO VARCHAR(255),\n" + "	ID_MUNICIPIO INT,\n"
				+ "	FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA(ID_CATEGORIA),\n"
				+ "	FOREIGN KEY (ID_MUNICIPIO) REFERENCES MUNICIPIO(ID_MUNICIPIO)\n);\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE IF NOT EXISTS USUARIO (\n" + "	NOME VARCHAR(255) NOT NULL,\n"
				+ "	EMAIL VARCHAR(255) PRIMARY KEY NOT NULL,\n" + "	SENHA VARCHAR(255) NOT NULL\n);\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE IF NOT EXISTS INDICACAO_PONTO_TURISTICO (\n"
				+ "	ID_INDICACAO_PONTO_TURISTICO INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n"
				+ "	NOME VARCHAR(255) NOT NULL,\n" + "	LATITUDE VARCHAR(255),\n" + "	LONGITUDE VARCHAR(255),\n"
				+ "	ALTITUDE VARCHAR(255),\n" + "	ID_CATEGORIA INT,\n" + "	DESCRICAO LONGTEXT,\n"
				+ "	IDIOMA VARCHAR(255),\n" + "	LOGRADOURO VARCHAR(255),\n" + "	ID_MUNICIPIO INT,\n"
				+ "	INDICACAO_ACEITA BIT NOT NULL DEFAULT 0,\n" + "	EMAIL VARCHAR(255) NOT NULL,\n"
				+ "	FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA(ID_CATEGORIA),\n"
				+ "	FOREIGN KEY (ID_MUNICIPIO) REFERENCES MUNICIPIO(ID_MUNICIPIO),\n"
				+ "	FOREIGN KEY (EMAIL) REFERENCES USUARIO(EMAIL)\n);\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE IF NOT EXISTS LOG (\n"
				+ "	ID_LOG INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" + "	ACAO VARCHAR(10) NOT NULL,\n"
				+ "	EMAIL VARCHAR(255) NOT NULL,\n" + "	REGISTRO VARCHAR(255) NOT NULL,\n"
				+ "	DATA_HORA CHAR(19) NOT NULL,\n" + "	FOREIGN KEY (EMAIL) REFERENCES USUARIO(EMAIL)\n" + ");\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE TABLE FAVORITO(\n"
				+ "	ID_FAVORITO INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" + "	EMAIL VARCHAR(255) NOT NULL,\n"
				+ "	ID_PONTO_TURISTICO INT NOT NULL,\n" + "	FOREIGN KEY (EMAIL) REFERENCES USUARIO(EMAIL),\n"
				+ "	FOREIGN KEY (ID_PONTO_TURISTICO) REFERENCES PONTO_TURISTICO(ID_PONTO_TURISTICO)\n" + ");\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE VIEW VIEW_PONTO_CATEGORIA AS\n"
				+ "SELECT ID_CATEGORIA, COUNT(ID_PONTO_TURISTICO) AS 'QUANTIDADE'\n" + "FROM   PONTO_TURISTICO\n"
				+ "GROUP BY ID_CATEGORIA;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE VIEW VIEW_PONTO_MUNICIPIO AS\n"
				+ "SELECT ID_MUNICIPIO, COUNT(ID_PONTO_TURISTICO) AS 'QUANTIDADE'\n" + "FROM   PONTO_TURISTICO\n"
				+ "GROUP BY ID_MUNICIPIO;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE VIEW VIEW_PONTO_CATEGORIA_MUNICIPIO AS\n"
				+ "SELECT ID_CATEGORIA, ID_MUNICIPIO, COUNT(ID_PONTO_TURISTICO) AS 'QUANTIDADE'\n"
				+ "FROM   PONTO_TURISTICO\n" + "GROUP BY ID_CATEGORIA, ID_MUNICIPIO;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE EVENT IF NOT EXISTS UPDATE_VIEW_PONTO_CATEGORIA\n"
				+ "ON SCHEDULE EVERY 24 HOUR\n" + "STARTS SYSDATE()\n" + "DO\n" + "	REPLACE VIEW_PONTO_CATEGORIA\n"
				+ "	SELECT ID_CATEGORIA, COUNT(ID_PONTO_TURISTICO) AS 'QUANTIDADE'\n" + "	FROM   PONTO_TURISTICO\n"
				+ "	GROUP BY ID_CATEGORIA;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE EVENT IF NOT EXISTS UPDATE_VIEW_PONTO_MUNICIPIO\n"
				+ "ON SCHEDULE EVERY 24 HOUR\n" + "STARTS SYSDATE()\n" + "DO\n"
				+ "	REPLACE VIEW_PONTO_CATEGORIA_MUNICIPIO\n"
				+ "	SELECT ID_MUNICIPIO, COUNT(ID_PONTO_TURISTICO) AS 'QUANTIDADE'\n" + "	FROM   PONTO_TURISTICO\n"
				+ "	GROUP BY ID_MUNICIPIO;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE EVENT IF NOT EXISTS UPDATE_VIEW_PONTO_CATEGORIA_MUNICIPIO\n"
				+ "ON SCHEDULE EVERY 24 HOUR\n" + "STARTS SYSDATE()\n" + "DO\n"
				+ "	REPLACE VIEW_PONTO_CATEGORIA_MUNICIPIO\n"
				+ "	SELECT ID_CATEGORIA, ID_MUNICIPIO, COUNT(ID_PONTO_TURISTICO) AS 'QUANTIDADE'\n"
				+ "	FROM   PONTO_TURISTICO\n" + "	GROUP BY ID_CATEGORIA, ID_MUNICIPIO;\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "CREATE EVENT IF NOT EXISTS INSERIR_INDICACAO_PONTO_TURISTICO\n"
				+ "ON SCHEDULE EVERY 144 HOUR\n" + "STARTS SYSDATE()\n" + "DO\n"
				+ "	INSERT INTO PONTO_TURISTICO(NOME,LATITUDE,LONGITUDE,ALTITUDE,ID_CATEGORIA,DESCRICAO,IDIOMA,LOGRADOURO,ID_MUNICIPIO)\n"
				+ "	(SELECT NOME, LATITUDE, LONGITUDE, ALTITUDE, ID_CATEGORIA, DESCRICAO, IDIOMA, LOGRADOURO, ID_MUNICIPIO\n"
				+ "	FROM   INDICACAO_PONTO_TURISTICO\n" + "	WHERE  INDICACAO_ACEITA = 1);\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		cabecalhoSql = cabecalhoSql + "INSERT INTO USUARIO VALUES ('SYSADM','SYSADM@SYSADM','UFRPEADM20172');\n";
		cabecalhoSql = cabecalhoSql + "INSERT INTO USUARIO VALUES ('SYSADM2','SYSADM2@SYSADM',MD5('UFRPEADM20172'));\n";
		cabecalhoSql = cabecalhoSql + imprimirLinha();

		return cabecalhoSql;
	}

	private static String criaTabelaMunicipio() {
		String sqlTabelaMunicipio = "";

		// Gerando INSERTS para os municípios pernambucanos
		for (int i = 0; i < MUNICIPIOS_PERNAMBUCANOS.length; i++) {
			int qtdMunicipios = i;
			qtdMunicipios = qtdMunicipios + 1;
			sqlTabelaMunicipio = sqlTabelaMunicipio + "/* Registro MUNICÍPIO Número: " + qtdMunicipios + "*/\n";
			sqlTabelaMunicipio = sqlTabelaMunicipio + "INSERT INTO MUNICIPIO (MUNICIPIO) " + "VALUES ('"
					+ MUNICIPIOS_PERNAMBUCANOS[i] + "');\n";
		}

		return sqlTabelaMunicipio;
	}

	private static String criaTabelaCategoria() {
		String sqlTabelaCategoria = "";

		// Gerando INSERTS para os municípios pernambucanos
		for (int i = 0; i < CATEGORIAS.length; i++) {
			int qtdCategoria = i;
			qtdCategoria = qtdCategoria + 1;
			sqlTabelaCategoria = sqlTabelaCategoria + "/* Registro CATEGORIA Número: " + qtdCategoria + "*/\n";
			sqlTabelaCategoria = sqlTabelaCategoria + "INSERT INTO CATEGORIA (CATEGORIA) " + "VALUES ('" + CATEGORIAS[i]
					+ "');\n";
		}

		return sqlTabelaCategoria;
	}

	private static String criaTabelaPontoTuristico(int numRegistro, String NOME, String LATITUDE, String LONGITUDE,
			String ALTITUDE, int idCategoria, String DESCRICAO, String IDIOMA, String NOMELOGRADOURO, int idMunicipio) {
		String sqlTabelaPontoTuristico = "";

		// Gerando INSERTS para os municípios pernambucanos
		sqlTabelaPontoTuristico = sqlTabelaPontoTuristico + "/* Registro DADOS Número: " + numRegistro + "*/\n";

		sqlTabelaPontoTuristico = sqlTabelaPontoTuristico + "INSERT INTO PONTO_TURISTICO (NOME,LATITUDE,LONGITUDE,"
				+ "ALTITUDE,ID_CATEGORIA,DESCRICAO,IDIOMA,LOGRADOURO,ID_MUNICIPIO) " + "VALUES ('" + NOME + "','"
				+ LATITUDE + "','" + LONGITUDE + "','" + ALTITUDE + "'," + idCategoria + ",'" + DESCRICAO + "','"
				+ IDIOMA + "','" + NOMELOGRADOURO + "'," + idMunicipio + ");\n";

		return sqlTabelaPontoTuristico;
	}

	private static int getIdMunicipio(String municipio) {
		int id = 1;

		for (int j = 0; j < MUNICIPIOS_PERNAMBUCANOS.length; j++) {
			if (municipio.toLowerCase().equals(MUNICIPIOS_PERNAMBUCANOS[j].toLowerCase())) {
				id = (j + 1);
				break;
			}
		}

		return id;
	}

	private static int getIdCategoria(String categoria) {
		int id = 1;

		for (int j = 0; j < CATEGORIAS.length; j++) {
			if (categoria.toLowerCase().equals(CATEGORIAS[j].toLowerCase())) {
				id = (j + 1);
				break;
			}
		}

		return id;
	}

	private static String extratorXmlParaSql() {
		String retornoSql = "";

		try {
			// Definindo objetos e preparando para leitura do XML dos DADOS
			File xmlDados = new File(caminhoXmlDados);
			DocumentBuilderFactory factoryDados = DocumentBuilderFactory.newInstance();
			DocumentBuilder builderDados = factoryDados.newDocumentBuilder();
			Document documentDados = builderDados.parse(xmlDados);
			documentDados.getDocumentElement().normalize();

			// Declarando variáveis necessárias para o extrator dos DADOS
			NodeList nlData;
			NodeList nlAbrangencia;
			NodeList nlRegistros;
			Node node;
			Element element;
			String dataCriacao;
			String dataPublicacao;
			String dataAbrangencia;
			String dataAbrangenciaInicio;
			String dataAbrangenciaFim;
			String dataHoje;
			Date hoje;
			String NOME;
			String LATITUDE;
			String LONGITUDE;
			String ALTITUDE;
			String CATEGORIA;
			String DESCRICAO;
			String IDIOMA;
			String NOMELOGRADOURO;
			String NOMEMUNICIPIO;

			// Leitura das datas de criação, publicação e abrangência do XML de
			// DADOS
			nlData = documentDados.getElementsByTagName(TAG_DATA);
			nlAbrangencia = documentDados.getElementsByTagName(TAG_ABRANGENCIA);
			nlRegistros = documentDados.getElementsByTagName(TAG_REGISTRO);
			node = nlData.item(0);
			element = (Element) node;
			dataCriacao = element.getAttribute(TAG_DATA_CRIACAO);
			dataPublicacao = element.getAttribute(TAG_DATA_PUBLICACAO);
			node = nlAbrangencia.item(0);
			element = (Element) node;
			dataAbrangencia = element.getAttribute(TAG_ABRANGENCIA_TEMPORAL);
			String[] auxDataAbrangencia = dataAbrangencia.split("/");
			dataAbrangenciaInicio = auxDataAbrangencia[0];
			dataAbrangenciaFim = auxDataAbrangencia[1];
			hoje = new Date();
			SimpleDateFormat simpleDateFormat;
			simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			dataHoje = simpleDateFormat.format(hoje);

			// Preenchendo retorno com as datas de criação, publicação e
			// abrangência dos dados
			// Gerando INSERTS com as informações de criação, publicação e
			// abrangência extraídos do XML
			// da Secretaria de Turismo do Estado de Pernambuco
			retornoSql = criaCabecalhoSql(dataCriacao, dataPublicacao, dataAbrangenciaInicio, dataAbrangenciaFim,
					dataHoje);

			// Gerando INSERTS para os municípios pernambucanos
			retornoSql = retornoSql + imprimirLinha();
			retornoSql = retornoSql + criaTabelaMunicipio();

			// Gerando INSERTS para as categorias de turismo
			retornoSql = retornoSql + imprimirLinha();
			retornoSql = retornoSql + criaTabelaCategoria();

			// Gerando INSERTS com as informações obtidas dos DADOS
			retornoSql = retornoSql + imprimirLinha();
			for (int i = 0; i < nlRegistros.getLength(); i++) {
				node = nlRegistros.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;
					NOME = element.getAttribute(TAG_CAMPO2);
					LATITUDE = element.getAttribute(TAG_CAMPO4);
					LONGITUDE = element.getAttribute(TAG_CAMPO5);
					ALTITUDE = element.getAttribute(TAG_CAMPO6);
					CATEGORIA = element.getAttribute(TAG_CAMPO7);
					DESCRICAO = element.getAttribute(TAG_CAMPO8);
					IDIOMA = element.getAttribute(TAG_CAMPO9);
					NOMELOGRADOURO = element.getAttribute(TAG_CAMPO10);
					NOMEMUNICIPIO = element.getAttribute(TAG_CAMPO11);

					int idCategoria = getIdCategoria(CATEGORIA);
					int idMunicipio = getIdMunicipio(NOMEMUNICIPIO);

					int numRegistro = i;
					numRegistro = numRegistro + 1;
					retornoSql = retornoSql + criaTabelaPontoTuristico(numRegistro, NOME, LATITUDE, LONGITUDE, ALTITUDE,
							idCategoria, DESCRICAO, IDIOMA, NOMELOGRADOURO, idMunicipio);
				}
			}

			retornoSql = retornoSql + imprimirLinha();
			retornoSql = retornoSql
					+ "INSERT INTO LOG(ACAO,EMAIL,REGISTRO,DATA_HORA) VALUES ('INICIAR','SYSADM@SYSADM','BANCO DE DADOS INICIADO','"
					+ dataHoje + "');";

		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}

		return retornoSql;
	}

}
