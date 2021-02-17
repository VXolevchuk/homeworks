package ua.kiev.prog.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ua.kiev.prog.chatrooms.ChatRoomsList;

public class MessageList {
	private static final MessageList msgList = new MessageList();

    private final Gson gson;
	private final ArrayList<Message> list = new ArrayList<>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  	private MessageList() {
		gson = new GsonBuilder().create();
	}
	
	public synchronized void add(Message m) {
		list.add(m);
	}


	
	public synchronized String toJSON(int n) {
		if (n >= list.size()) return null;
		return gson.toJson(new JsonMessages(list, n));
	}

	public synchronized String toJSON(String login, int n) {
		List<Message> msgList = list.stream()
				.filter(message -> Objects.equals(login, message.getFrom())
						|| (Objects.equals("all", message.getTo()) && !Objects.equals(login, message.getFrom()))
						|| Objects.equals(login, message.getTo())
						|| ChatRoomsList.getChatRooms(login).contains(message.getChatRoom()))
				.collect(Collectors.toList());
		if (n >= msgList.size())
			return null;
		return gson.toJson(new JsonMessages(msgList, n));
	}



}
