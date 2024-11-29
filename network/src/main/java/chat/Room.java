package chat;

import java.util.List;
import java.util.Vector;

public class Room {
    private final String roomName;
    private User owner;
    private List<User> users;

    public Room(String roomName, User user) {
        this(roomName);
        users.add(user);
        owner = user;
    }

    public User getOwner() {
        return owner;
    }

    public Room(String roomName) {
        this.roomName = roomName;
        this.users = new Vector<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public String getRoomName() {
        return roomName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}
