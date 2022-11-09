package uz.murodjon.notificationbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.murodjon.notificationbot.model.MessageDTO;
import uz.murodjon.notificationbot.model.Users;
import uz.murodjon.notificationbot.payload.UserDTO;
import uz.murodjon.notificationbot.repository.MarketRepository;
import uz.murodjon.notificationbot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final MarketRepository marketRepository;
    private final UserRepository userRepository;
    private final BotService botService;

    public List<Users> getUsersByMarketUsername(String username) {
        List<Users> usersList = new ArrayList<>();
        Long marketId = marketRepository.getMarketIdByUsername(username);
        List<Long> usersByMarketId = marketRepository.getUsersByMarketId(marketId);
        usersByMarketId.forEach(id -> usersList.add(userRepository.findById(id).get()));
        return usersList;
    }

    public void sendMessageToUser(MessageDTO messageDTO,String username) {
        botService.sendMessageToUser(messageDTO,username);
    }

}
