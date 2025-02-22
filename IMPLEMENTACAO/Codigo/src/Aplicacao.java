package app;

import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		Universidade universidade = new Universidade();
		Files files = new Files();
		files.carregarCursos(universidade);
		files.carregarAlunos(universidade);
		files.carregarDisciplinas(universidade);
		
		files.carregarProfessores(universidade);
		files.carregarOfertas(universidade);
		files.carregarSecretarios(universidade);
		
		validarLogin(universidade, teclado);
	}

	public static void validarLogin(Universidade universidade, Scanner teclado) {
		System.out.println("\nUNIVERSIDADE X \n");
		System.out.println("Insira seu usu�rio: ");
		String opcao = teclado.next();
		String senha = "x";
		User user = null;

		for(User u : universidade.getUsers()) {
			if (opcao.equals(u.getUsuario())){
				user = u;
				senha = u.getSenha();
			}
		}

		System.out.println("Insira sua senha");
		opcao = teclado.next();

		if (senha.equals(opcao)) {
			if (user instanceof Secretario) {
				menuSecretario(universidade, teclado, user);
			}
			else if(user instanceof Aluno) {
				menuAluno(universidade, teclado, user);
			}
			else {
				menuProfessor(universidade, teclado, user);
			}
		}
		else {
			System.out.println ("Usu�rio digitado inv�lido!");
			teclado.nextLine();
			validarLogin(universidade, teclado);
		}
	}

	public static void menuSecretario(Universidade univ, Scanner teclado, User u) {
		System.out.println("\nBEM VINDO " + u.getNome() + "\n");
		System.out.println("1 - Cadastrar Aluno");
		System.out.println("2 - Cadastrar Professor");
		System.out.println("3 - Cadastrar Curso");
		System.out.println("4 - Cadastrar Disciplinas");
		System.out.println("5 - Remover Aluno");
		System.out.println("6 - Remover Professor");
		System.out.println("7 - Remover Curso");
		System.out.println("8 - Remover Disciplina");
		System.out.println("9 - Modificar Registro de um aluno");
		System.out.println("10 - Modificar Registro de um professor");
		System.out.println("11 - Modificar Registro de um curso");
		System.out.println("12 - Modificar Registro de uma disciplina");
		System.out.println("13 - Imprimir Dados");
		System.out.println("0 - Sair");
		System.out.println("Insira uma op��o:");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		do {
			switch (opcao) {
			case 1:
				cadastrarAluno(teclado, univ, u);
				break;
			case 2:
				cadastrarProfessor(teclado, univ, u);
				break;
			case 3:
				cadastrarCurso(teclado, univ, u);
				break;
			case 4: 
				cadastrarDisciplina (teclado, univ, u);
				break;
			case 5:
				removerAluno(teclado, univ, u);
				break;
			case 6:
				removerProfessor(teclado, univ, u);
				break;
			case 7:
				removerCurso(teclado, univ, u);
				break;
			case 8:
				removerDisciplina(teclado, univ, u);
				break;
			case 9:
				updateAluno(teclado, univ, u);
				break;
			case 10:
				updateProfessor(teclado, univ, u);
				break;
			case 11:
				updateCurso(teclado, univ, u);
				break;
			case 12:
				updateDisciplina(teclado, univ, u);
				break;
			case 13:
				imprimirDados(teclado, univ, u);
				break;
			}
		}while(opcao != 0);
		validarLogin(univ, teclado);
	}

	private static void updateDisciplina(Scanner teclado, Universidade univ, User u) {
		String pathDisciplinas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\disciplinas.txt";
		String pathOfertas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\ofertas.txt";
		Files files = new Files();
		System.out.println("\nSelecione uma disciplina para editar:");
		int i = 1;
		for(Disciplina d: univ.getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int op = teclado.nextInt();
		
		String nomeDisc = "";
		i = 1;
		for(Disciplina d: univ.getDisciplinas()) {
			if(i == op) {
				nomeDisc = d.getNome();
			}
			i++;
		}
		Disciplina d = univ.getDisciplinaPorNome(nomeDisc);
		
		System.out.println("\nDISCIPLINA:" + d.getNome() + "\n");
		
		System.out.println("Nome atual: " + d.getNome());
		System.out.println("Digite o novo nome: ");
		String resp = teclado.next();
		d.setNome(resp);
		teclado.nextLine();
		
		System.out.println("Valor atual: " + d.getValor());
		System.out.println("Digite o novo valor: ");
		d.setValor(teclado.nextDouble());
		teclado.nextLine();

		System.out.println("\nDisciplina Atualizada!");
		
		files.transfArrayListParaDisciplinas(pathDisciplinas, univ);
		files.transfArrayListParaOferta(pathOfertas, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
		
	}

	private static void updateCurso(Scanner teclado, Universidade univ, User u) {
		Files files = new Files();
		String pathCursos = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\cursos.txt";
		String pathDisciplinas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\disciplinas.txt";
		String pathOfertas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\ofertas.txt";
		System.out.println("\nSelecione um curso para editar:");
		int i = 1;
		for(Curso c: univ.getCursos()) {
			System.out.println(i + "-" + c.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int op = teclado.nextInt();
		
		String nomeCurso = "";
		i = 1;
		for(Curso c: univ.getCursos()) {
			if(i == op) {
				nomeCurso = c.getNome();
			}
			i++;
		}
		Curso c = univ.getCursoPorNome(nomeCurso);
		
		System.out.println("\nCURSO:" + c.getNome() + "\n");
		
		System.out.println("Nome atual: " + c.getNome());
		System.out.println("Digite o novo nome: ");
		String resp = teclado.next();
		c.setNome(resp);
		teclado.nextLine();

		System.out.println("N�mero de Cr�ditos atual: " + c.getNumCreditos());
		System.out.println("Digite a nova quantidade de cr�ditos: ");
		c.setNumCreditos(teclado.nextInt());
		teclado.nextLine();
		
		System.out.println("Disciplinas do curso: ");
		i = 1;
		for(Disciplina d: c.getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}
		
		int opcao = 1;
		do {
			System.out.println("Deseja inserir uma disciplina?");
			System.out.println("1 - Sim");
			System.out.println("2 - N�o");
			System.out.println("Insira uma op��o:");
			opcao = teclado.nextInt();
			if (opcao == 1) {
				System.out.println("\nDisciplinas dispon�veis:");
				i = 1;
				for(Disciplina d: univ.getDisciplinas()) {
					System.out.println(i + "-" + d.getNome());
					i++;
				}
				System.out.println("Insira uma op��o:");
				op = teclado.nextInt();

				Disciplina d = univ.getDisciplinas().get(op - 1);
				c.addDisciplina(d);			
				teclado.nextLine();
			}
		}while(opcao != 2);
		teclado.nextLine();
		
		System.out.println("\nCurso Atualizado!");
		
		files.transfArrayListParaCursos(pathCursos, univ);
		files.transfArrayListParaDisciplinas(pathDisciplinas, univ);
		files.transfArrayListParaOferta(pathOfertas, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}

	private static void updateProfessor(Scanner teclado, Universidade univ, User u) {
		Files files = new Files();
		String pathProfessores = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\professores.txt";
		String pathOfertas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\ofertas.txt";
		String pathDisciplinas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\disciplinas.txt";
		
		System.out.println("\nSelecione um professor para editar:");
		int i = 1;
		for(Professor p: univ.getProfessores()) {
			System.out.println(i + "-" + p.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int op = teclado.nextInt();
		
		String nomeProfe = "";
		i = 1;
		for(Professor p: univ.getProfessores()) {
			if(i == op) {
				nomeProfe = p.getNome();
			}
			i++;
		}
		Professor p = univ.getProfessorPorNome(nomeProfe);
		
		System.out.println("\nPROFESSOR:" + p.getNome() + "\n");
		
		System.out.println("Usu�rio atual: " + p.getUsuario());
		System.out.println("Digite o novo usu�rio: ");
		p.setUsuario(teclado.next());
		teclado.nextLine();
		
		System.out.println("Senha atual: " + p.getSenha());
		System.out.println("Digite a nova senha: ");
		p.setSenha(teclado.next());
		teclado.nextLine();
		
		System.out.println("Nome atual: " + p.getNome());
		System.out.println("Digite o novo nome: ");
		p.setNome(teclado.nextLine());
		
		i = 1;
		System.out.println("Disciplina ministrada: ");
		for(Oferta o: p.getOfertasMinistradas()) {
			System.out.println(i + "-" + o.getDisciplina().getNome());
			i++;
		}
		System.out.println("\nDisciplina nova a ser ministrada:");
		i = 1;
		for(Disciplina d: univ.getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int opcao = teclado.nextInt();
		Disciplina d = univ.getDisciplinas().get(opcao - 1);
		Oferta o = d.getOfertas().get(0);
		p.addOferta(o);
		teclado.nextLine();
		
		System.out.println("\nProfessor Atualizado!");
		
		files.transfArrayListParaProfessores(pathProfessores, univ);
		files.transfArrayListParaOferta(pathOfertas, univ);
		files.transfArrayListParaDisciplinas(pathDisciplinas, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}

	private static void updateAluno(Scanner teclado, Universidade univ, User u) {
		String pathAlunos = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\alunos.txt";
		Files files = new Files();
		System.out.println("\nSelecione um aluno para editar:");
		int i = 1;
		for(Aluno a: univ.getAlunos()) {
			System.out.println(i + "-" + a.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int op = teclado.nextInt();
		
		String nomeAluno = "";
		i = 1;
		for(Aluno a: univ.getAlunos()) {
			if(i == op) {
				nomeAluno = a.getNome();
			}
			i++;
		}
		Aluno a = univ.getAlunoPorNome(nomeAluno);
		
		System.out.println("\nALUNO:" + a.getNome() + "\n");
		
		System.out.println("Usu�rio atual: " + a.getUsuario());
		System.out.println("Digite o novo usu�rio: ");
		a.setUsuario(teclado.next());
		teclado.nextLine();
		
		System.out.println("Senha atual: " + a.getSenha());
		System.out.println("Digite a nova senha: ");
		a.setSenha(teclado.next());
		teclado.nextLine();
		
		System.out.println("Nome atual: " + a.getNome());
		System.out.println("Digite o novo nome: ");
		a.setNome(teclado.nextLine());
		
		System.out.println("Curso atual: " + a.getCurso().getNome());
		System.out.println("Escolha o novo curso: ");
		i = 1;
		for(Curso c: univ.getCursos()) {
			System.out.println(i + "-" + c.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int opcao = teclado.nextInt();
		Curso c = univ.getCursos().get(opcao - 1);
		a.setCurso(c);

		teclado.nextLine();

		System.out.println("C�digo do aluno atual: " + a.getCodigoDoAluno());
		System.out.println("Digite o novo c�digo: ");
		a.setCodigoDoAluno(teclado.nextInt());
		teclado.nextLine();
		
		System.out.println("Per�odo do curso atual: " + a.getperiodo());
		System.out.println("Digite o novo per�odo do curso: ");
		a.setPeriodo(teclado.nextInt());
		teclado.nextLine();

		System.out.println("\nAluno atualizado!");
		
		files.transfArrayListParaAlunos(pathAlunos, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);	
	}

	private static void cadastrarCurso(Scanner teclado, Universidade univ, User u) {
		Files files = new Files();
		String path = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\cursos.txt";
		String content = "";
		
		Curso c = new Curso();
		System.out.println("\nCADASTRAR CURSO:\n");

		System.out.println("Nome:");
		c.setNome(teclado.nextLine());

		content = c.getNome();
		content = content.concat(",");
		
		System.out.println("N�mero de Cr�ditos:");
		c.setNumCreditos(teclado.nextInt());
		teclado.nextLine();

		content = content.concat(Integer.toString(c.getNumCreditos()));
		content = content.concat(",");
		
		int opcao = 1;
		do {
			System.out.println("Deseja inserir uma disciplina?");
			System.out.println("1 - Sim");
			System.out.println("2 - N�o");
			System.out.println("Insira uma op��o:");
			opcao = teclado.nextInt();
			if (opcao == 1) {
				System.out.println("\nDisciplinas dispon�veis:");
				int i = 1;
				for(Disciplina d: univ.getDisciplinas()) {
					System.out.println(i + "-" + d.getNome());
					i++;
				}
				System.out.println("Insira uma op��o:");
				int op = teclado.nextInt();
				
				if(!(content.endsWith(","))) {
					content = content.concat(";");
				}
				
				Disciplina d = univ.getDisciplinas().get(op - 1);
				c.addDisciplina(d);;
				
				content = content.concat(d.getNome());
				
				
				teclado.nextLine();
			}
		}while(opcao != 2);
		System.out.println(content);
		univ.addCurso(c);
		files.escreveArquivo(path, content);
		System.out.println("Curso Cadastrado!\n");
		
		teclado.nextLine();
		
		System.out.println("Curso:" + c.getNome());
		int i = 1;
		System.out.println("Disciplinas: \n");
		for(Disciplina d: c.getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);

	}
	
	public static void removerCurso (Scanner teclado, Universidade univ, User u) {
		
		String pathCursos = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\cursos.txt";
		String pathDisciplinas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\disciplinas.txt";
		Files files = new Files();
		
		System.out.println("\nQual curso deseja remover? ");
		int i = 1;
		for(Curso c: univ.getCursos()) {
			System.out.println(i + "-" + c.getNome());
			i++;
		}
		int op = teclado.nextInt();
		i = 1;
		String nomeCurso = "";
		for(Curso c: univ.getCursos()) {
			if (i == op) {
				nomeCurso = c.getNome();		
			}
			i++;
		}
		univ.removeCurso(univ.getCursoPorNome(nomeCurso));
		
		files.transfArrayListParaCursos(pathCursos, univ);
		files.transfArrayListParaDisciplinas(pathDisciplinas, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}

	private static void cadastrarDisciplina (Scanner teclado, Universidade univ, User u) {
		Files files = new Files();
		String path = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\disciplinas.txt";
		String pathCursos = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\cursos.txt";
		String pathOfertas = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\ofertas.txt";
		
		//falta escrever no curso.txt a disciplina cadastrada - NAO MAIS
		String content = "";
		String contentCursos = "";
		String contentOfertas = "";
		
		System.out.println("\nEm qual curso deseja cadastrar nova disciplina? ");
		int i = 1;
		for(Curso c: univ.getCursos()) {
			System.out.println(i + "-" + c.getNome());
			i++;
		}
		int op = teclado.nextInt();
		i = 1;
		System.out.println("\nNome da disciplina: ");
		String name = teclado.next();
		
		content = name;
		content = content.concat(",");
		
		System.out.println("\n� uma disciplina optativa? ");
		String optativa = teclado.next();
		
		System.out.println("\nQual o valor dessa disciplina? ");
		double valor = teclado.nextDouble();
		
		boolean optativ = isOptativa(optativa);
		for(Curso c: univ.getCursos()) {
			if (i == op) {
				Disciplina nova = new Disciplina (name, optativ, valor);
				content = content.concat(Boolean.toString(optativ)).concat(",");
				content = content.concat(Double.toString(valor).concat(",")).concat("false").concat(", ,");
				System.out.println("Qual o id da oferta? ");
				int id = teclado.nextInt();
				content = content.concat(Integer.toString(id));
				System.out.println(content);
				c.addDisciplina(nova);
				univ.addDisciplina(nova);
				files.escreveArquivo(path, content);
				
				String nomeCurso = c.getNome();
				contentCursos = name;
				files.escreveDisciplinaNoCurso(nomeCurso, pathCursos, contentCursos, univ);
				contentOfertas = Integer.toString(id);
				contentOfertas = contentOfertas.concat(",").concat(nova.getNome()).concat(",");
				System.out.println(contentOfertas);
				files.escreveArquivo(pathOfertas, contentOfertas);
				System.out.println("Disciplina adicionada!");
			}
			i++;
		}
		for(Disciplina d: univ.getCursos().get(0).getDisciplinas()) {
			System.out.println(d.getNome());
		}
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}
	
	private static boolean isOptativa (String optativa) {
		if (optativa.equalsIgnoreCase("sim")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void removerDisciplina (Scanner teclado, Universidade univ, User u) {
		String pathDisciplinas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\disciplinas.txt";
		String pathOfertas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\ofertas.txt";
		Files files = new Files();
		System.out.println("\nQual disciplina deseja remover? ");
		int i = 1;
		for(Disciplina d: univ.getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}
		int op = teclado.nextInt();
		i = 1;
		String nomeDisciplina = "";
		for(Disciplina d: univ.getDisciplinas()) {
			if (op == i) {
				nomeDisciplina = d.getNome();
			}
			i++;
		}
		univ.removeDisciplina(univ.getDisciplinaPorNome(nomeDisciplina));
		
		files.transfArrayListParaDisciplinas(pathDisciplinas, univ);
		files.transfArrayListParaOferta(pathOfertas, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}
	
	private static void cadastrarProfessor(Scanner teclado, Universidade univ, User u) {
		Files files = new Files();
		String path = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\professores.txt";
		String pathOfertas= "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\ofertas.txt";
		String pathUsers= "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\users.txt";
		String pathDisciplinas= "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\disciplinas.txt";
		
		String content = "";
		String contentOferta = "";
		String contentUsers= "";
		
		Professor p = new Professor();
		System.out.println("\nCADASTRAR PROFESSOR:\n");

		System.out.println("Usu�rio:");
		p.setUsuario(teclado.next());
		teclado.nextLine();

		content = p.getUsuario();
		content = content.concat(",");
		
		System.out.println("Senha:");
		p.setSenha(teclado.next());
		teclado.nextLine();

		content = content.concat(p.getSenha());
		contentUsers = content;
		content = content.concat(",");
		
		System.out.println("Nome:");
		p.setNome(teclado.nextLine());;
		
		content = content.concat(p.getNome());
		String contentNome = p.getNome();
		content = content.concat(",");
		
		System.out.println("Disciplina a ser ministrada:");
		int i = 1;
		for(Disciplina d: univ.getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int opcao = teclado.nextInt();
		Disciplina d = univ.getDisciplinas().get(opcao - 1);
		Oferta o = d.getOfertas().get(0);
		System.out.println(o.getId());
		p.addOferta(o);;
		univ.getDisciplinas().get(opcao - 1).getOfertas().get(0).setProfessor(p);;
		for(Curso c: univ.getCursos()) {
			for(Disciplina dis: c.getDisciplinas()) {
				if (dis.getNome().equals(d.getNome())){
					System.out.println(dis.getNome() + " = " + d.getNome());
					dis.getOfertas().get(0).setProfessor(p);
				}
			}
		}
		
		String contentId = Integer.toString(p.getOfertasMinistradas().get(0).getId());
		String contentNomeDisciplina = p.getOfertasMinistradas().get(0).getDisciplina().getNome();
		
		contentOferta = contentId.concat(",").concat(contentNomeDisciplina).concat(",").concat(contentNome);
		System.out.println(contentOferta);
		
		content = content.concat(Integer.toString(p.getOfertasMinistradas().get(0).getId()));
		
		teclado.nextLine();

		univ.addProfessor(p);
		o.setProfessor(p);
		files.escreveArquivo(path, content);
		
		//essa porra aqui V
		
		//files.escreveArquivo(pathOfertas, contentOferta);
		
		//files.escreveProfessorEmOferta(o.getId(), pathOfertas, univ, d);
		
		files.escreveOfertaNaDisciplina(d.getNome(), pathDisciplinas, univ);
		
		files.escreveProfessorEmOferta(o.getId(), pathOfertas, univ, d);
		
		files.escreveArquivo(pathUsers, contentUsers);
		
		System.out.println(content);
		System.out.println("Professor Cadastrado!");
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}
	
	private static void removerProfessor (Scanner teclado, Universidade univ, User u) {
		Files files = new Files();
		String pathProfessores = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\professores.txt";
		String pathOfertas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\ofertas.txt";
		String pathDisciplinas = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\disciplinas.txt";
		
		System.out.println("\nQual professor deseja remover? ");
		int i = 1;
		for(Professor p: univ.getProfessores()) {
			System.out.println(i + "-" + p.getNome());
			i++;
		}
		int op = teclado.nextInt();
		i = 1;
		String nomeProfessor = "";
		for(Professor p: univ.getProfessores()) {
			if (i == op) {
				nomeProfessor = p.getNome();
			}
			i++;
		}
		univ.removeProfessor(univ.getProfessorPorNome(nomeProfessor));
		
		files.transfArrayListParaProfessores(pathProfessores, univ);
		files.transfArrayListParaOferta(pathOfertas, univ);
		files.transfArrayListParaDisciplinas(pathDisciplinas, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}

	private static void cadastrarAluno(Scanner teclado, Universidade univ, User u) {
		
		String path = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\alunos.txt";
		String pathUsers = "C:\\Users\\Isabel Pinheiro\\Documents\\Eng de software\\4�per�odo\\eclipse\\LAB01\\src\\app\\users.txt";
		
		Files files = new Files();
		String content = "";
		String contentUsers = "";
		
		Aluno a = new Aluno();
		System.out.println("\nCADASTRAR ALUNO:\n");
		
		System.out.println("Usu�rio:");
		a.setUsuario(teclado.next());
		teclado.nextLine();

		content = a.getUsuario();
		content = content.concat(",");
		
		System.out.println("Senha:");
		a.setSenha(teclado.next());
		teclado.nextLine();

		content = content.concat(a.getSenha());
		contentUsers = content;
		content = content.concat(",");
		
		System.out.println("Nome:");
		a.setNome(teclado.nextLine());

		content = content.concat(a.getNome());
		content = content.concat(",");
		
		System.out.println("Curso:");
		int i = 1;
		for(Curso c: univ.getCursos()) {
			System.out.println(i + "-" + c.getNome());
			i++;
		}
		System.out.println("Insira uma op��o:");
		int opcao = teclado.nextInt();
		Curso c = univ.getCursos().get(opcao - 1);
		a.setCurso(c);
		
		content = content.concat(a.getCurso().getNome());
		content = content.concat(",");
		
		teclado.nextLine();

		System.out.println("C�digo do aluno:");
		a.setCodigoDoAluno(teclado.nextInt());
		teclado.nextLine();

		content = content.concat(Integer.toString(a.getCodigoDoAluno()));
		content = content.concat(",");
		
		System.out.println("Per�odo do curso:");
		a.setPeriodo(teclado.nextInt());
		teclado.nextLine();

		content = content.concat(Integer.toString(a.getperiodo()));
		
		files.escreveArquivo(path, content);
		files.escreveArquivo(pathUsers, contentUsers);
		
		univ.addAluno(a);

		System.out.println("Aluno cadastrado!");
		teclado.nextLine();
		menuSecretario(univ, teclado, u);

	}
	
	private static void removerAluno (Scanner teclado, Universidade univ, User u) {
		String pathAlunos = "C:\\Users\\Daniel\\Desktop\\Lab1\\Lab1Project\\src\\app\\alunos.txt";
		Files files = new Files();
		System.out.println("\nQual aluno deseja remover? ");
		int i = 1;
		for(Aluno a: univ.getAlunos()) {
			System.out.println(i + "-" + a.getNome());
			i++;
		}
		int op = teclado.nextInt();
		i = 1;
		String nomeAluno = "";
		for(Aluno a: univ.getAlunos()) {
			if(i == op) {
				nomeAluno = a.getNome();
			}
			i++;
		}
		univ.removeAluno(univ.getAlunoPorNome(nomeAluno));
		
		files.transfArrayListParaAlunos(pathAlunos, univ);
		
		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}

	private static void imprimirDados(Scanner teclado, Universidade univ, User u) {
		System.out.println("\nALUNOS: ");
		int i = 1;
		for(Aluno a: univ.getAlunos()) {
			System.out.println(i + "-" + a.getNome());
			System.out.println("     - Curso: " + a.getCurso().getNome());
			System.out.println("     - Per�odo: " + a.getperiodo());
			System.out.println("     - C�digo do aluno: " + a.getCodigoDoAluno());
			i++;
		}

		System.out.println("\nPROFESSORES: ");
		i = 1;
		for(Professor p: univ.getProfessores()) {
			System.out.println(i + "- " + p.getNome());
			System.out.println("   Disciplinas ministradas: ");
			for(Oferta o:p.getOfertasMinistradas()) {
				System.out.println("     - " + o.getDisciplina().getNome());
			}
			i++;
		}

		System.out.println("\nCURSOS: ");
		i = 1;
		for(Curso c: univ.getCursos()) {
			System.out.println(i + "- " + c.getNome());
			System.out.println("   Cr�ditos: " + c.getNumCreditos());
			System.out.println("   Disciplinas do curso: ");
			for(Disciplina d: c.getDisciplinas()) {
				System.out.println("     - " + d.getNome());
			}
			i++;
		}

		System.out.println("\nDISCIPLINAS: ");
		i = 1;
		for(Disciplina d: univ.getDisciplinas()) {
			System.out.println(i + "- " + d.getNome());
			System.out.println("     - Valor: " + d.getValor());
			System.out.println("     - Alunos: ");
			for(Aluno a: d.getAlunos()) {
				System.out.println("          - " + a.getNome());
			}
			i++;
		}

		teclado.nextLine();
		menuSecretario(univ, teclado, u);
	}


	public static void menuAluno(Universidade univ, Scanner teclado, User u) {
		Aluno a = (Aluno) u;

		System.out.println("\nBEM VINDO " + u.getNome() + "\n");
		System.out.println("1 - Efetuar matr�cula");
		System.out.println("2 - Minhas disciplinas");
		System.out.println("3 - Informa��es de pagamento");
		System.out.println("0 - Sair");
		System.out.println("Insira uma op��o:");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		do {
			switch (opcao) {
			case 1:
				efetuarMatricula(univ, teclado, a);
				break;
			case 2:
				imprimirDisciplinas(univ, teclado, opcao, a);
				break;
			case 3:
				imprimirCustoDisciplinas(univ, teclado, a);
				break;
			}
		}while(opcao != 0);
		validarLogin(univ, teclado);
	}

	private static void imprimirCustoDisciplinas(Universidade univ, Scanner teclado, Aluno a) {
		System.out.println("\nDisciplinas matriculadas e custo individual: \n");
		double soma = 0;

		int i = 1;
		for(Oferta o: a.getOfertasMatriculadas()) {
			System.out.println(i + "-" + o.getDisciplina().getNome() + " -> Custo: R$" + o.getDisciplina().getValor());
			soma += o.getDisciplina().getValor();
			i++;
		}

		System.out.println("\nValor total: R$" + soma);

		teclado.nextLine();
		menuAluno(univ, teclado, a);

	}

	private static void imprimirDisciplinas(Universidade univ, Scanner teclado, int opcao, Aluno a) {
		System.out.println("\nDisciplinas matriculadas: ");

		int i = 1;
		for(Oferta o: a.getOfertasMatriculadas()) {
			System.out.println(i + "-" + o.getDisciplina().getNome());
			i++;
		}

		teclado.nextLine();
		menuAluno(univ, teclado, a);
	}

	private static void efetuarMatricula(Universidade univ, Scanner teclado, Aluno a) {

		System.out.println("Disciplinas dispon�veis: ");

		int i = 1;
		for(Disciplina d : a.getCurso().getDisciplinas()) {
			System.out.println(i + "-" + d.getNome());
			i++;
		}

		System.out.println("\nInsira uma op��o: ");

		int opcao = teclado.nextInt();
		teclado.nextLine();

		Disciplina disc = a.getCurso().getDisciplinas().get(opcao - 1);
		Oferta oferta = disc.getOfertas().get(0);

		a.addOferta(oferta);
		oferta.getDisciplina().addAluno(a);

		System.out.println("Matr�cula efetuada!");
		teclado.nextLine();
		menuAluno(univ, teclado, a);
	}

	public static void menuProfessor(Universidade univ, Scanner teclado, User u) {
		Professor p = (Professor) u;

		System.out.println("\nBEM VINDO " + u.getNome() + "\n");
		System.out.println("Suas disciplinas: ");

		int i = 1;
		for(Oferta o : p.getOfertasMinistradas()) {
			System.out.println(i + "-" + o.getDisciplina().getNome());
			i++;
		}
		System.out.println("0-Sair");

		System.out.println("\nInsira uma op��o: ");

		int opcao = teclado.nextInt();
		teclado.nextLine();
		if(opcao == 0) {
			validarLogin(univ, teclado);
		}
		imprimirAlunos(univ, teclado, opcao, p);
		teclado.nextLine();
	}

	public static void imprimirAlunos(Universidade univ, Scanner teclado, int opcao, Professor p) {
		int i = 1;
		Oferta oferta = p.getOfertasMinistradas().get(opcao - 1);

		System.out.println("\nAlunos cursando a disciplina: ");

		for(Aluno a: oferta.getDisciplina().getAlunos()) {
			System.out.println(i + "-" + a.getNome());
			i++;
		}

		teclado.nextLine();
		menuProfessor(univ, teclado, p);
	}

}
