package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import Model.Account;
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
        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);

        // message endpoints
        app.get("messages", this::getAllMessagesHandler);
        app.get("messages/{message_id}", this::getMessageByIdHandler);
        app.post("messages", this::createMessageHandler);
        app.patch("messages/{message_id}", this::updateMessageHandler);
        app.delete("messages/{message_id}", this::deleteMessageHandler);
        app.get("accounts/{account_id}/messages", this::getAllMessageByAccountIdHandler);

        return app;
    }

    // for register new user
    private void registerHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account register_account = accountService.register(account);
        if (register_account != null){
            context.json(mapper.writeValueAsString(register_account));
        }
        context.status(400);
    }

    // for user login
    private void loginHandler(Context context) {

        context.json("sample text");
    }

    // getting all message
    private void getAllMessagesHandler(Context context) {

        context.json("sample text");
    }

    // getting a message by id
    private void getMessageByIdHandler(Context context) {

        context.json("sample text");
    }

    // creating a new message
    private void createMessageHandler(Context context) {

        context.json("sample text");
    }

    //updating a message
    private void updateMessageHandler(Context context) {

        context.json("sample text");
    }

    // deleting a message
    private void deleteMessageHandler(Context context) {

        context.json("sample text");
    }

    // get all messages of a user
    private void getAllMessageByAccountIdHandler(Context context) {

        context.json("sample text");
    }
}