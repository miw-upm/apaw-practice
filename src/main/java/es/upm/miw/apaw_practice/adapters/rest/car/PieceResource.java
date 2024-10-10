package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import es.upm.miw.apaw_practice.domain.services.car.PieceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PieceResource.PIECES)
public class PieceResource {

    static final String PIECES = "/pieces";

    private PieceService pieceService;

    public PieceResource(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @PostMapping()
    public ResponseEntity<Void> post(@RequestBody Piece piece) {
        if (pieceService.existsPartNumber(piece.getPartNumber())) {
            throw new BadRequestException("Part number '" + piece.getPartNumber() + "' already exists.");
        }
        pieceService.create(piece);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
