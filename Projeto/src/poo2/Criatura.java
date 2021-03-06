    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo2;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Vinicius
 */
public class Criatura implements Subject
{
    //Os valores de alimentacao, higiene e diversão variam de 0 a 100 e diminuem a saude se forem menos que 0 ou maiores que 100.
    
    int saude; // Se chegar a 0 bichinho morre.
    int nivel; 
    String nome;
    int idade=  0;
    int fome;
    int higiene; 
    int felicidade = 0;
    int[] multiplicador;
    Acessorio cadeia;
    Acessorio atual;    
    FasesDaVida faseatual;
      
    public Criatura(){
        this.setFase(new Crianca());
        this.saude = this.faseatual.getSaudeMaxima();
        this.nivel = 0;
        this.fome = 0;
        this.higiene = 100;
        this.felicidade = 0;
        //Timer tm = new Timer();
        //tm.schedule(teste(), faseatual.tempoD * 1000);        
    }
       
    public void Alimentar(Alimento al)  
    {
        this.fome += multiplicador[0] * al.Alimentar();
        atualizaSaude(20);
        atualizaNivel(30);
    }
    
    public void Banho()
    {
        this.higiene += 50;
        atualizaSaude(-30);
        atualizaNivel(20);
    }
    
    public void Banheiro()
    {
        this.higiene += 10;
        atualizaSaude(-5);
        atualizaNivel(5);
    }
    
    public void Brincar() {
        this.felicidade += multiplicador[2] * 10;
    }    
    
    public void setFase(FasesDaVida fase) 
    {
        this.faseatual = fase;
    }    
    public void subirDeFase(){
        setFase(this.faseatual.subirDeFase());
    }
    
    public void cairDeFase(){
        setFase(this.faseatual.cairDeFase());
    }
    
    public void atualizaSaude(int valor) {
        this.saude -= this.multiplicador[1]*valor;
        if(this.saude<=0)
        {
            this.cairDeFase();
        }
    }
    
    public void atualizaNivel(int valor) {
        this.nivel += valor;
        if(this.nivel >= faseatual.getNivelMaximo())
        {
            this.subirDeFase();
        }
    }    
    
    public ArrayList<Observer> observadores;

    @Override
    public void registrarObservador(Observer o) {
        observadores.add(o);
    }

    @Override
    public void removerObservador(Observer o) {
        int i = observadores.indexOf(o);
        if(i>=0){
            observadores.remove(o);
        }
    }

    @Override
    public void notificarObservador() {
        for(int i=0; i < observadores.size();i++){
            Observer obs = (Observer) observadores.get(i);
            obs.atualizar(nome, nivel, fome, saude, felicidade, higiene);
        }
    }
    
    
    
    
}
