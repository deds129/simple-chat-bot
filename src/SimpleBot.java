import java.lang.reflect.AnnotatedArrayType;
import java.util.*; //
import java.util.regex.*; //библиотека работы с регулярными выражениями



 class SimpleBot {
     //набор констант; строк
     final String[] COMMON_PHRASES={ //массив констант
             "Нет ничего ценнее слов, сказанных к месту и ко времени.",
             "Порой молчание может сказать больше, нежели уйма слов",
             "Перед тем как писать/говорить всегда лучше хорошо подумать.",
             "Вежливая и грамотная речь говорит о величии души.",
             "Приятно когда текст без орфографических ошибок.",
             "Слова могут ранить, но могут и исцелять",
             "Записывая слова, мы удваиваем их силу",
             "Кто ясно мыслит, тот ясно излагает",
             "Боюсь Вы что-то недоговариваете."};
     final String[] ELUSIVE_ANSWERS={
             "Вопрос непростой, дайте время на раздумья.",
             "Как грубо с вашей стороны!",
             "Это все что вы хотели спросить?",
             "Может поговорим о чем-нибудь другом?",
             "Поверьте, я сам хотел бы знать ответ на этот вопрос.",
             "Спосите это у Чудинова Николая.",
             "Уверен, что вы уже догазались сами).",
             "Зачем вам такая информация?",
             "Давайте сохраним интригу."};
     final Map<String,String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>(){{ //шаблоны для анализа, ключ левая часть, правая пара значений, левая часть - это строка, которая используется для регулярных выражений
         //если найдется слово используется выражение с ключом найдено "Здравствуй" ключ "how are you"
         //hello
         put("здравстуй","hello");//hello - ключ, на ключ есть массив: ответы по ключам
         put("привет","hello");
         put("здорова","hello");
         put("приветик","hello");
         put("приветствую","hello");

         //who
         put("кто\\s.*ты", "who");
         put("ты\\s.*кто", "who");

         //name
         put("как\\s.*зовут","name");
         put("как\\s.*имя","name");
         put("есть\\s.*имя","name");
         put("какое\\s.*имя","name");
         put("назови\\s.*имя","name");

         //howareyou
         put("как\\s.*дела","howareyou");
         put("как\\s.*жизнь","howareyou");

         //whatdoyoudoing
         put("зачем\\s.*тут","whatdoyoudoing");
         put("зачем\\s.*здесь","whatdoyoudoing");
         put("что\\s.*делаешь","whatdoyoudoing");
         put("чем\\s.*занимаешься","whatdoyoudoing");
         put("как" + " " + "проводишь\\s.*день","whatdoyoudoing"); //Проверочка))

         //whatdoyoulike
         put("что\\s.*нравится","whatdoyoulike");
         put("что\\s.*любишь","whatdoyoulike");

         //iamfeeling
         put("кажется","iamfeeling");
         put("чувствую","iamfeeling");
         put("испытываю","iamfeeling");

         //yes
         put("да","yes");
         put("согласен","yes");

         //whattime
         put("который\\s.*час","whattime");
         put("сколько\\s.*веремени","whattime");

         //bye
         put("прощай","bye");
         put("увидимся","bye");
         put("до\\.s*свидания","bye");
     }};

     final Map<String,String> ANSWERS_BY_PATTERN = new HashMap<String, String>(){{
         put("hello","Здравствуйте, Рад вас видеть.");
         put("who","Здравствуйте, рад вас видеть");
         put("name","Зовите меня Чат от первого брака =)");
         put("howareyou","Cпасибо что интересуетесь у меня все хорошо.");
         put("whatdoyoudoing","Я пробую общаться с людьми.");
         put("whatdoyoulike","Мне нравится думать что я нечто большее чем просто программа.");
         put("iamfeeling","Как давно это началось? Расскажите подробнее.");
         put("yes","Согласие есть продукт полного согласования сторон.");
         put("bye","До свидания. Надеюсь мыы еще увидимся");


     }};





     //объявляем переменные;
     Pattern pattern;
     Random random;
     Date date;



     //создаем коструктор
  public SimpleBot() {
      random = new Random(); // создаем объект random для того чтобы
      date = new Date(); // объект чтобы иметь дату
  }
  //запрогали метод sayInReturn
     String sayInReturn(String msg, boolean ai){ // передача строки нашего сообщения
    String say = (msg.trim().endsWith("?"))? //функция "endWith" Заканчивается ли строка на определенный знак
            ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]: //конструкция получения случайного элемента массива без учета количества эл массива
            COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
    if(ai) { //проверка включения чекбокса Ai
        String message  =
                String.join(" ", msg.toLowerCase().split("[ {,|.}?]+")); //берем строку и переводим все в нижний регистор + съедаем знаки препинания и пробелы
        //получаем строку для проверки

        //анализ ; поиск шаблонов
        for (Map.Entry<String,String> o : PATTERNS_FOR_ANALYSIS.entrySet()) { //перебор шаблонов для анализа
        pattern = Pattern.compile(o.getKey()); //берем ключ ; левую часть
        if (pattern.matcher(message).find())//вызываем метод matcher + метод main
            if (o.getValue().equals("whattime")) return date.toString();
            else return ANSWERS_BY_PATTERN.get(o.getValue());
        }
    }

    return say;
    }
 }
