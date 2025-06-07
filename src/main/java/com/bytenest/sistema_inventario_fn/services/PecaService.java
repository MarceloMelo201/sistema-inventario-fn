package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.dtos.PecaDto;
import com.bytenest.sistema_inventario_fn.model.entities.PecaModel;
import com.bytenest.sistema_inventario_fn.model.stock.MovimentacaoModel;
import com.bytenest.sistema_inventario_fn.repositories.PecaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PecaService {
    private final PecaRepository pecaRepository;

    @Autowired
    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    @Transactional
    public PecaModel salvarPeca(PecaDto pecaDto, MovimentacaoModel movimentacaoModel){

            PecaModel pecaModel = PecaModel.builder()
                    .nome(pecaDto.nome())
                    .valor(pecaDto.valor())
                    .sku(pecaDto.sku())
                    .descricao(pecaDto.descricao())
                    .quantidadeTotal(pecaDto.quantidadeTotal())
                    .build();

            pecaModel.addMovimentacao(movimentacaoModel);
            return pecaRepository.save(pecaModel);
    }

    public List<PecaModel> listarTodasAsPecas(){
        List<PecaModel> listPeca = pecaRepository.findAll();
        return listPeca;
    }

    public PecaModel listarPeca(UUID id){
        PecaModel peca = pecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada."));
        return peca;
    }

    public PecaModel listarPecaSku(String sku){
        PecaModel peca = pecaRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada."));
        return peca;
    }

    @Transactional
    public PecaModel atualizarPeca(UUID id, PecaDto pecaDto){
        PecaModel peca = pecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada."));

        BeanUtils.copyProperties(pecaDto, peca);
        return pecaRepository.save(peca);
    }

    @Transactional
    public void deletarPeca(UUID id){
        PecaModel peca = pecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada."));
        pecaRepository.delete(peca);
    }


}
