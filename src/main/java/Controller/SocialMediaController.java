package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;
    // contructor
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // account endpoints
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);

        // message endpoints
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.post("/messages", this::createMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessageByAccountIdHandler);

        return app;
    }

    // account handlers
    // for register new user
    private void registerHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        
        Account register_account = accountService.register(account);
        if (register_account != null){
            context.json(mapper.writeValueAsString(register_account));
        } else{
            context.status(400); 
        }
    }

    // for user login
    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account login_account = accountService.login(account);
        if (login_account != null){
            context.json(mapper.writeValueAsString(login_account));
        } else{
            context.status(401); 
        }
    }

    // message handlers
    // getting all message
    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    // getting a message by id
    private void getMessageByIdHandler(Context context) {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageById(message_id);
        if (message != null){
            context.json(message);
        } else {
            context.result();
        }
    }

    // creating a new message
    private void createMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message created_Message = messageService.createNewMessage(message); 
        if (created_Message != null){
            context.json(mapper.writeValueAsString(created_Message));
        } else{
            context.status(400); 
        }
    }

    //updating a message
    private void updateMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message updated_Message = messageService.updateExistingMessage(message_id, message);
        if (updated_Message != null){
            context.json(mapper.writeValueAsString(updated_Message));
        } else{
            context.status(400); 
        }
    }

    // deleting a message
    private void deleteMessageHandler(Context context) {
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.deleteExistingMessage(message_id);
        if (message != null){
            context.json(message);
        } else {
            context.status(200);
        }
        
    }

    // get all messages of a user
    private void getAllMessageByAccountIdHandler(Context context) {
        int account_id = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getAllUserMessages(account_id);
        context.json(messages);
    }
}