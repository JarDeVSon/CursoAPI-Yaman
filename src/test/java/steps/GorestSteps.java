package steps;


import api.Header;
import api.Request;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import maps.Users;
import org.json.JSONObject;
import utils.JsonUtils;
import utils.PropertiesUtils;

import static api.ApiBody.initBodyUsers;
import static org.junit.Assert.assertEquals;


public class GorestSteps extends Request {

    PropertiesUtils prop = new PropertiesUtils();
    Header apiHeaders = new Header();
    Users payload;
    JsonUtils jsonUtils = new JsonUtils();

    @Dado("que possuo gorest token valido")
    public void quePossuoGorestTokenValido() {
        token = prop.getProp("token");
    }

    @Entao("o status code deve ser {int}")
    public void oStatusCodeDeveSer(int statusEsperado) {
        assertEquals(statusEsperado, response.statusCode());
    }


    @Quando("envio uma request de cadastro de usuario com dados validos")
    public void envioUmaRequestDeCadastroDeUsuarioComDadosValidos() {
        super.uri = prop.getProp("uri");
        super.headers = apiHeaders.gorestHeader(token);

        super.body = new JSONObject(new Gson().toJson(initBodyUsers()));
        super.POST();
    }


    @Entao("o usuario deve ser criado corretamente,")
    public void oUsuarioDeveSerCriadoCorretamente() {
        assertEquals(payload, response.jsonPath().getObject("data", Users.class));
//        assertEquals(super.body.getString("name"), response.jsonPath().getString("data.name"));

    }
    @Dado("tenho um usuario cadastrado na api")
    public void tenhoUmUsuarioCadastradoNaApi() {
       envioUmaRequestDeCadastroDeUsuarioComDadosValidos();
    }

    @Quando("envio uma request de busca de usuario")
    public void envioUmaRequestDeBuscaDeUsuario() {
        super.uri = prop.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = apiHeaders.gorestHeader(token);
        super.body = new JSONObject();
        super.GET();
    }

    @Entao("o usuario deve ser retornado corretamente")
    public void oUsuarioDeveSerRetornadoCorretamente() {
        assertEquals(payload, response.jsonPath().getObject("data", Users.class));

    }
    @Quando("envio uma request de alteracao dos dados do usuario")
    public void envioUmaRequestDeAlteracaoDosDadosDoUsuario() {
        super.uri = prop.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = apiHeaders.gorestHeader(token);
        super.body.put("status","inactive");

        super.PUT();
    }

    @Entao("os dados do usuario deve ser alterado corretamente")
    public void osDadosDoUsuarioDeveSerAlteradoCorretamente() {
        assertEquals(payload, response.jsonPath().getObject("data", Users.class));

    }

    @Quando("altero um ou mais dados do usuario")
    public void alteroUmOuMaisDadosDoUsuario() {
        super.uri = prop.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = apiHeaders.gorestHeader(token);
        payload.setGender("male");
        super.body = new JSONObject("{\"gender\":\"male\"}");
        super.PATCH();
    }
    @Quando("envio uma request de alteracao de um ou mais dados do usuario")
    public void envioUmaRequestDeAlteracaoDeUmOuMaisDadosDoUsuario() {
        super.uri = prop.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = apiHeaders.gorestHeader(token);
        super.body.put("gender","male");

        super.PATCH();
    }

    @Quando("deleto esse usuario")
    public void deletoEsseUsuario() {
        super.uri = prop.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = apiHeaders.gorestHeader(token);
        super.body = new JSONObject();
        super.DELETE();
    }

    @Entao("o usuario deve ser deletado corretamente")
    public void oUsuarioDeveSerDeletadoCorretamente() {
        assertEquals("", response.asString());
    }
}