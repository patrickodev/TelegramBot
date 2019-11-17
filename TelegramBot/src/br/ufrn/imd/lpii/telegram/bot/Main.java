package br.ufrn.imd.lpii.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class Main {

    public static void main(String[] args){

        //Criação do objeto bot com as informações de acesso
        TelegramBot bot = TelegramBotAdapter.build("977190748:AAFf3cMIian_wMJd_SrASeonHRzISjAK_ws");

        //objeto responsável por receber as mensagens
        GetUpdatesResponse updatesResponse;
        //objeto responsável por gerenciar o envio de respostas
        SendResponse sendResponse;
        //objeto responsável por gerenciar o envio de ações do chat
        BaseResponse baseResponse;
        
        Patrimonio patrimonio;
        Localizacao localizacao;
        Bem bem;
        Categoria categoria;
        
        patrimonio = new Patrimonio();
        //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
        int m=0;
        
        String getComandos = "Lista de comandos do Bota Patrimônio:\n" +
                "/start - exibe lista de comandos\n" +
                "/cadastrarLoc\n" +
                "/cadastrarCat\n" +
                "/cadastrarBem\n" +
                "/listarLoc\n" +
                "/listarCat\n" +
                "/listarBensPorLoc\n" +
                "/buscarBemPorCod\n" +
                "/buscarBemPorNome\n" +
                "/buscarBemPorDesc\n" +
                "/movimentarBem\n" +
                "/relatorio";
        
        //loop infinito pode ser alterado por algum timer de intervalo curto
        while (true){

            //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
            updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));

            //lista de mensagens
            List<Update> updates = updatesResponse.updates();

            //análise de cada ação da mensagem
            for (Update update : updates) {
            	
            	//atualização do off-set
                m = update.updateId()+1;
	            baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	            //baseResponse = bot.execute(new SendMessage(update.message().chat().id(), "Escrevendo..."));                          
                 
                switch (update.message().text()){
                    case "/start":
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(), getComandos));
                        break;
                    case "/cadastrarLoc":
                    	localizacao = new Localizacao();
                    	baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Cadastrando Localização..."));
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Codigo: "));
                        localizacao.setCodigo(update.message().text());
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Nome: "));
                        localizacao.setNome(update.message().text());
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Descricao: "));
                        localizacao.setDescricao(update.message().text());
                        break;
                    case "/cadastrarCat":
                    	baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Cadastrando Categoria, informe o que for pedido..."));
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Nome: "));
                        categoria = new Categoria();
                        break;
                    case "/cadastrarBem":
                    	baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Cadastrando Bem..."));
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Nome: "));
                        bem = new Bem();
                        break;
                    case "/listarLoc":
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(), patrimonio.listarLocalizacoes()));
                        break;
                    case "/listarCat":
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(), patrimonio.listarCategorias()));
                        break;
                    case "/listarBens":
                    	baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Clique no link da localização desejada:\n"));
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(), patrimonio.listarLocalizacoes()));
                        break;
                    case "/buscarBemPorLoc":
                        //Falta implementar
                        break;
                    case "/buscarBemPorNome":
                    	//Falta implementar
                        break;
                    case "/buscarBemPorDesc":
                    	//Falta implementar
                        break;
                    case "/movimentarBem":
                    	//Falta implementar
                        break;
                    case "/relatorio":
                    	//Falta implementar
                        break;
                    default:
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),"Não entendi... Utilize um comando existente\n"));
                        baseResponse = bot.execute(new SendMessage(update.message().chat().id(),getComandos));
                        break;
                }                
        
        }
                
       


        }        
    	}
}
