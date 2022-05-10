#language: pt

  @regressivo
  Funcionalidade: Criar e editar contas de usuarios
    Eu como administrador do sistema, Quero cadastrar, editar e excluir usuários do sistema
  @post
  Cenario: Cadastrar um novo usuário na API Gorest
    Dado que possuo gorest token valido
    Quando envio uma request de cadastro de usuario com dados validos
    Entao o usuario deve ser criado corretamente,
    E o status code deve ser 201

    @get
    Cenario: Buscar um usuario existente na API Gorest
      Dado que possuo gorest token valido
      E tenho um usuario cadastrado
      Quando envio uma request de busca de usuario
      Entao o usuario deve ser retornado corretamente
      E o status code deve ser 200

      @put
      Cenario: Alterar um usuario existente na API Gorest PUT
        Dado que possuo gorest token valido
        E tenho um usuario cadastrado
        Quando envio uma request de alteração dos dados do usuario
        Entao os dados do usuario deve ser alterado corretamente
        E o status code deve ser 200

        @patch
        Cenario: Alterar um usuario existente na API Gorest PATCH
          Dado que possuo gorest token valido
          E tenho um usuario cadastrado
          Quando envio uma request de alteração de um ou mais dados do usuario
          Entao os dados do usuario deve ser alterado corretamente
          E o status code deve ser 200