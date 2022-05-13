package domain.entities.cliente;

public class Cliente {
    //TODO Adicionar Campo ID
    private String cpf;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String endereco;
    private String status;

    public Cliente(String cpf, String nomeCompleto, String telefone, String email, String endereco, String status) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
