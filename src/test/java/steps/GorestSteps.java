package steps;

import api.Header;
import api.Request;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import maps.Users;
import org.json.JSONObject;
import utils.JsonUtils;
import utils.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GorestSteps extends Request {
    Faker faker = new Faker();
    Header header = new Header();
    PropertiesUtils propertiesUtils = new PropertiesUtils();
    JsonUtils jsonUtils = new JsonUtils();
    Map<String, String> jsonValues = new HashMap<>();
    Users users;

    @Dado("que possuo gorest token valido")
    public void quePossuoGorestTokenValido() {
        super.token = propertiesUtils.getProp("token");
    }

    @E("tenho um usuario cadastrado")
    public void tenhoUmUsuarioCadastrado() {
        quePossuoGorestTokenValido();
        envioUmaRequestDeCadastroDeUsuarioComDadosValidos();
        oUsuarioDeveSerCriadoCorretamente();
    }

    @Quando("envio uma request de cadastro de usuario com dados validos")
    public void envioUmaRequestDeCadastroDeUsuarioComDadosValidos() {
        super.uri = propertiesUtils.getProp("uri");
        super.headers = header.gorestHeader(token);
       Users users = Users.builder()
                .status("active")
                .gender("female")
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .build();
        super.body = new JSONObject(new Gson().toJson(users));

        //super.body = jsonUtils.setJsonValuesByKey(jsonUtils.parseJSONFile("teste"),setJsonValues());
        super.POST();
    }

    @Quando("envio uma request de busca de usuario")
    public void envioUmaRequestDeBuscaDeUsuario() {
        super.uri = propertiesUtils.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = header.gorestHeader(token);
        super.body = new JSONObject();

        super.GET();
    }

    @Quando("envio uma request de alteração dos dados do usuario")
    public void envioUmaRequestDeAlteraçãoDosDadosDoUsuario() {
        super.uri = propertiesUtils.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = header.gorestHeader(token);
        super.body.put("status","inactive");

        super.PUT();
    }
    @Quando("envio uma request de alteração de um ou mais dados do usuario")
    public void envioUmaRequestDeAlteraçãoDeUmOuMaisDadosDoUsuario() {
        super.uri = propertiesUtils.getProp("uri") + "/" + response.jsonPath().get("id");
        super.headers = header.gorestHeader(token);
        super.body.put("gender","male");

        super.PATCH();
    }

    @Entao("o usuario deve ser retornado corretamente")
    public void oUsuarioDeveSerRetornadoCorretamente() {
        assertEquals(users, response.jsonPath().getObject("data", Users.class));
    }

    @Entao("o usuario deve ser criado corretamente,")
    public void oUsuarioDeveSerCriadoCorretamente() {
      /*
        assertEquals(super.body.getString("email"),
                super.response.jsonPath().getString("email"));
        assertEquals(super.body.getString("name"),
                super.response.jsonPath().getString("name"));
        assertEquals(super.body.getString("gender"),
                super.response.jsonPath().getString("gender"));
        assertEquals(super.body.getString("status"),
                super.response.jsonPath().getString("status" +
                        ""));
*/
        assertEquals(users, response.jsonPath().getObject("data", Users.class));
    }

    @Entao("os dados do usuario deve ser alterado corretamente")
    public void osDadosDoUsuarioDeveSerAlteradoCorretamente() {
        assertEquals(users, response.jsonPath().getObject("data", Users.class));
    }

    @E("o status code deve ser {int}")
    public void oStatusCodeDeveSer(int status) {
        assertEquals(status, response.getStatusCode());
    }

    private Map<String, String> setJsonValues() {
        jsonValues.put("email", faker.internet().emailAddress());
        jsonValues.put("name", faker.name().fullName());
        jsonValues.put("gender", "female");
        jsonValues.put("status", "inactive");
        return jsonValues;
    }


}
