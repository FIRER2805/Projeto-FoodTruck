package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVo;

public class MenuUsuario {
	
	private static final int OPCAO_MENU_CADASTRAR_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO = 3;
	private static final int OPCAO_MENU_EXCLUIR_USUARIO = 4;
	private static final int OPCAO_MENU_USUARIO_VOLTAR = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_USUARIOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_USUARIO = 2;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR = 9;

	Scanner scan = new Scanner(System.in);
	
	public void apresentarMenuUsuario() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_USUARIO_VOLTAR)
		{
			switch(opcao)
			{
			case OPCAO_MENU_CADASTRAR_USUARIO:
				UsuarioVo usuarioVo = new UsuarioVo();
				this.cadastrarUsuario(usuarioVo);
				break;
			case OPCAO_MENU_CONSULTAR_USUARIO:
				this.consultarUsuario();
				break;
			case OPCAO_MENU_ATUALIZAR_USUARIO:
				this.atualizarUsuario();
				break;
			case OPCAO_MENU_EXCLUIR_USUARIO:
				this.excluirUsuario();
				break;
				default:
					System.out.println("\nOpção inválida!");
					break;
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\n---------- Sistema FoodTruck ----------");
		System.out.println("---------- Menu de Usuário ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_CADASTRAR_USUARIO + " - Cadastrar Usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO + " - Consultar Usuário");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO + " - Atualizar Usuário");
		System.out.println(OPCAO_MENU_EXCLUIR_USUARIO + " - Excluir Usuário");
		System.out.println(OPCAO_MENU_USUARIO_VOLTAR + " - Voltar");
		System.out.println("\nDigite um opção: ");
		return Integer.parseInt(scan.nextLine());
	}

	// Método que cadastra um usuário externo
	public void cadastrarNovoUsuario(UsuarioVo usuarioVo) {
		this.cadastrarUsuario(usuarioVo);
	}
	
	private void cadastrarUsuario(UsuarioVo usuarioVo) {
		if(usuarioVo.getTipoUsuario() == null)
		{
			do 
			{
				usuarioVo.setTipoUsuario(TipoUsuarioVO.getTipoUsuarioVOPorValor(this.apresentarOpcoesTipoUsuario()));
			} while(usuarioVo.getTipoUsuario() == null);
		}
		System.out.println("\nDigite o nome: ");
		usuarioVo.setNome(scan.nextLine());
		System.out.println("\nDigite o CPF: ");
		usuarioVo.setCpf(scan.nextLine());
		System.out.println("\nDigite o e-mail: ");
		usuarioVo.setEmail(scan.nextLine());
		System.out.println("\nDigite o telefone: ");
		usuarioVo.setTelefone(scan.nextLine());
		usuarioVo.setDataCadastro(LocalDateTime.now());
		System.out.println("\nDigite o login: ");
		usuarioVo.setLogin(scan.nextLine());
		System.out.println("\nDigite a senha: ");
		usuarioVo.setSenha(scan.nextLine());
		
		if(this.validarCamposCadastro(usuarioVo))
		{
			UsuarioController usuarioController = new UsuarioController();
			usuarioVo = usuarioController.cadastrarUsuarioController(usuarioVo);
			if(usuarioVo.getIdUsuario() != 0)
			{
				System.out.println("Usuário cadastrado com sucesso!");
			}
			else 
			{
				System.out.println("Não foi possivel cadastrar o usuário!");
			}
		}
	}


	private int apresentarOpcoesTipoUsuario() {
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<TipoUsuarioVO> listaTipoUsuarioVO = usuarioController.consultarTipoUsuario();
		System.out.println("\n---- Tipo de Usuários ----");
		System.out.println("Opções: ");
		for(int i = 0; i < listaTipoUsuarioVO.size(); i++)
		{
			System.out.println(listaTipoUsuarioVO.get(i).getValor() + " - " + listaTipoUsuarioVO.get(i));
		}
		System.out.println("\nDigite uma opção: ");
		return Integer.parseInt(scan.nextLine());
	}
	
	private boolean validarCamposCadastro(UsuarioVo usuarioVo) {
		boolean resultado = true;
		System.out.println();
		if(usuarioVo.getNome() == null || usuarioVo.getNome().isEmpty())
		{
			System.out.println("O campo nome é obrigatório");
			resultado = false;
		}
		if(usuarioVo.getCpf() == null || usuarioVo.getCpf().isEmpty())
		{
			System.out.println("O campo CPF é obrigatório");
			resultado = false;
		}
		if(usuarioVo.getEmail() == null || usuarioVo.getEmail().isEmpty())
		{
			System.out.println("O campo e-mail é obrigatório");
			resultado = false;
		}
		if(usuarioVo.getTelefone() == null || usuarioVo.getTelefone().isEmpty())
		{
			System.out.println("O campo telefone é obrigatório");
			resultado = false;
		}
		if(usuarioVo.getDataCadastro() == null)
		{
			System.out.println("O campo data de cadastro é obrigatório");
			resultado = false;
		}
		if(usuarioVo.getLogin() == null || usuarioVo.getLogin().isEmpty())
		{
			System.out.println("O campo Login é obrigatório");
			resultado = false;
		}
		if(usuarioVo.getSenha() == null || usuarioVo.getSenha().isEmpty())
		{
			System.out.println("O campo senha é obrigatório");
			resultado = false;
		}
		return resultado;
	}

	private void consultarUsuario() {
		System.out.println("Consultando usuario");
	}

	private void atualizarUsuario() {
		System.out.println("Atualizando usuario");
	}

	private void excluirUsuario() {
		System.out.println("Excluindo usuario");
	}
	
}
