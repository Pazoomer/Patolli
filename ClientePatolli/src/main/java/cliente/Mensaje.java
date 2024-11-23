package cliente;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;

    private CuerpoMensaje body;
    private Usuario sender;
    private Usuario receiver;
    private TipoMensaje messageType;

    public Mensaje() {
    }

    public Mensaje(CuerpoMensaje body, Usuario sender, Usuario receiver, TipoMensaje messageType) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = messageType;
    }

    public CuerpoMensaje getBody() {
        return body;
    }

    public void setBody(CuerpoMensaje body) {
        this.body = body;
    }

    public Usuario getSender() {
        return sender;
    }

    public void setSender(Usuario sender) {
        this.sender = sender;
    }

    public Usuario getReceiver() {
        return receiver;
    }

    public void setReceiver(Usuario receiver) {
        this.receiver = receiver;
    }

    public TipoMensaje getMessageType() {
        return messageType;
    }

    public void setMessageType(TipoMensaje messageType) {
        this.messageType = messageType;
    }

    public static class Builder implements Serializable {

        private CuerpoMensaje body;
        private Usuario sender;
        private Usuario receiver;
        private TipoMensaje messageType;

        public Builder() {}
        
        public Builder body(CuerpoMensaje body) {
            this.body = body;
            return this;
        }
        
        public Builder sender(Usuario sender) {
            this.sender = sender;
            return this;
        }
        
        public Builder receiver(Usuario receiver) {
            this.receiver = receiver;
            return this;
        }
        
        public Builder messageType(TipoMensaje messageType) {
            this.messageType = messageType;
            return this;
        }
        
        public Mensaje build() {
            return new Mensaje(this.body, this.sender, this.receiver, this.messageType);
        }
    }
}
