/**
 * Чудинов Николай
 * ресурс: https://vk.com/video-111905078_456243330
 * Создание интерфейсной/графической оболочки
 * для Чат-бота
 * Бот выводит информацию в Гр.оболочке
 *Все работает локально
 * делаем все через swing = вешаем прослушиватель действий == нажимаем на кнопку - процедура срабатывает
 * 40:00
 */

import java.awt.*; // Импортируем 3 библиотеки для работы грфики
import java.awt.event.*;
import javax.swing.*;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

class SimpleChatBot extends JFrame implements ActionListener { // имя класса = имя файла ; класс наследуется(extends) от класса "JFrame"; Реализация интерфейса
    //константы final(т.е. объявление переменной,которая не будет меняться)
    final String TITLE_OF_PROGRAMM="Chatter: simple chatbot";// Заголовок программы
    final int START_LOCATION=200; //константы всегда пишутся большыми буквами и разделяются подчеркиваниями
    final int WINDOW_WIDTH=350;
    final int WINDOW_HEIGHT=450;
    //объявление переменных: Грфические объекты, которые будут появляться на экране
    JTextArea dialogue;
    JCheckBox ai;
    JTextField message;
SimpleBot sbot; //объявляем бота
//SimpleAttributeSet botStyle;

    public static void main(String[] args) { //метод main срабатывает всегода когда Запускается программа "Пускач"
        new SimpleChatBot();//создание объекта из класса
    }

    //Создаем констуктор; Имя объекта(класса) совпадает с именем конструктора
    SimpleChatBot() {
        setTitle(TITLE_OF_PROGRAMM); // вызов заголовка программы
        setDefaultCloseOperation(EXIT_ON_CLOSE); //определяет завершение программы нажатием на крестик
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_WIDTH); //устанавливает ориентацию окна


//AREA FOR DIALOGUE
        dialogue=new JTextArea();//
        dialogue.setLineWrap(true); // перенос строк true
        JScrollPane scrollBar=new JScrollPane(dialogue); //вертикальный скроллинг + заворачиваем


//STYLE FOR BOT MESSAGES
//     botStyle = new SimpleAttributeSet();
//    StyleConstants.setItalic(botStyle, true);
//    StyleConstants.setForeground(botStyle, Color.blue);

        //StyleConstants.setAlignment(botStyle, StyleContants.ALIGN_RIGHT);

        //panel for checkbox,message field and button
        //создаем панели + размещаем элементы
        JPanel bp=new JPanel(); //panel name bp
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));//элементы добавленные будут размещаться по горизонтали; компановщик
        ai=new JCheckBox("AI");
        // ai.doClick();
        message=new JTextField(); // поле ввода реплик
        message.addActionListener(this); // процедура ActionPerformed начинает срабатывать когды мы нажмем на Enter в поле Performed
        JButton enter=new JButton("Enter"); // создание кнопки
        enter.addActionListener(this); // определяем компонент с именем enter + регистр события на этой  кнопке


        //adding all elements to the window
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);// работает на все окно
        add(BorderLayout.SOUTH, bp); // работает на юг
        setVisible(true);
        sbot = new SimpleBot(); // создаем бота
    }

    //Реализация ИНТЕРФЕЙСА ActionListener.
    @Override //переопределение метода
    public void actionPerformed(ActionEvent event) { //переопределяем функцию аctionPerformed(возникает когда нажимают на кнопку
        if (message.getText().trim().length() > 0) { //берем(компонент message) сообщение + убираем пробелы + замеряем длину
//если длина больше 0 добавляем  строку в диалог
            dialogue.append(message.getText() + "\n"); //
            dialogue.append(TITLE_OF_PROGRAMM.substring(0,9) +
                    sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n"); //нужно создать метод ответа бота

            message.setText("");
            message.requestFocusInWindow();

        }
    }
}

