package com.codesa.registroEscolar.common;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Esto se usa para devolver errores en formato RFC 7807, que creó un esquema generalizado para el manejo de errores compuesto por 5 partes")
public class StandardizedApiExceptionResponse {
    @Schema(description = "El identificador URI único que categoriza el error", name="type",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "/error/authentication/not-authorized")
    private String type;

    @Schema(description = "Un mensaje breve y comprensible para humanos sobre el error", name="title",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "El usuario no cuenta con autorizacion")
    private String title;

    @Schema(description = "El código único de error", name="code", requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "192")
    private String code;

    @Schema(description = "Una explicación comprensible para humanos sobre el error", name = "detail",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "El usuario no cuenta con los permisos necesarios")
    private String detail;

    @Schema(description = "Una URI que identifica el error", name="instance", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "/errors/authentication/not-authorized/01")
    private String instance;

    public StandardizedApiExceptionResponse(String code, String detail, String instance, String title, String type) {
        super();
        this.code = code;
        this.detail = detail;
        this.instance = instance;
        this.title = title;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}