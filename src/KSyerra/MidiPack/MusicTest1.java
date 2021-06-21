package KSyerra.MidiPack;

import javax.sound.midi.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MusicTest1 {

    public static void main(String[] args) throws Exception {
        MusicTest1 mt = new MusicTest1();
        String userInput;

        while(true) {
            System.out.print("Введите число от 0 до 127: ");
            userInput = new BufferedReader(new InputStreamReader(System.in)).readLine();

            if(userInput.equals("exit")) {
                break;
            }
            int instrument = Integer.parseInt(userInput);
            System.out.print("Введите число от 0 до 127: ");
            userInput = new BufferedReader(new InputStreamReader(System.in)).readLine();

            if(userInput.equals("exit")) {
                break;
            }
            int note = Integer.parseInt(userInput);
            mt.play(instrument, note);
        }
    }

    public void play(int instrument, int note) {
        try {
            Sequencer player = MidiSystem.getSequencer(); //получаем синтезатор и открываем его
            player.open(); //открываем его, чтобы начать использовать, так как сначала он не открыт
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();//запрашиваем трек у последовательности

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage(); //создаём сообщение
            a.setMessage(144,1,note,100); //помещаем инструкцию с номером ноты (data1)
            MidiEvent noteOn = new MidiEvent(a, 1); //создаём новое событие, используя сообщение и хронометраж
            track.add(noteOn);//добавляем созданное нами событие в трек

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOff = new MidiEvent(b, 16);
            track.add(noteOff);


            player.setSequence(seq); //передаём последовательность синтезатору (как будто вставляем CD в проигрыватель)
            player.start(); //запускаем синтезатор нажатием play

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;

        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {}

        return event;
    }
}
