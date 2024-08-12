package com.project.flashcard.learning.tool.card;

import com.project.flashcard.learning.tool.exceptions.CardNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class CardJpaResource {
    private CardRepository cardRepository;

    public CardJpaResource(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @GetMapping(path = "/cards")
    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }
    @GetMapping("/cards/{id}")
    public Card getCardById(@PathVariable long id) throws Exception{
        Optional<Card> cardMaybe = cardRepository.findById(id);
        if(cardMaybe.isEmpty()){
            throw new CardNotFoundException("Card With id "+id+" not found");
        }
        return cardMaybe.get();
    }
    @PostMapping("/cards")
    public ResponseEntity<Object> createCard(@Valid @RequestBody Card card){
        Card savedCard = cardRepository.save(card);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCard.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/cards/{id}")
    public void deleteACard(@PathVariable long id){
        cardRepository.deleteById(id);
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card cardDetails) {
        Optional<Card> optionalCard = cardRepository.findById(id);

        if (optionalCard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Card existingCard = optionalCard.get();
        existingCard.setWord(cardDetails.getWord());
        existingCard.setDescription(cardDetails.getDescription());

        Card updatedCard = cardRepository.save(existingCard);
        return ResponseEntity.ok(updatedCard);
    }
}
