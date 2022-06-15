package maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import org.json.JSONObject;
import org.json.XML;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {

    private String email;
    private String name;
    private String gender;
    private String status;

    public JSONObject getJson() {
        return new JSONObject(new Gson().toJson(this));
    }

    public String getXml() {
        return XML.toString(getJson());
    }
}
