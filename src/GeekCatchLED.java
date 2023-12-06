import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

public class GeekCatchLED implements SerialPortDataListener {
    private SerialPort serialPort;
    public static String name;
    public static String message;
    public static int amount;
    public static String animation;
    public static String color;
    public static String font;
    public static String delay;
    public static enum Animations
    {
        RANDOM_CYCLE("A"), STATIC("B"), OPEN_FROM_RIGHT("C"), OPEN_FROM_LEFT("D"),
        OPEN_FROM_CENTER("E"), OPEN_TO_CENTER("F"), COVER_FROM_CENTER("G"),
        COVER_FROM_RIGHT("H"), COVER_FROM_LEFT("I"), COVER_TO_CENTER("J"), SCROLL_UP("K"),
        SCROLL_DOWN("L"), INTERLACE_TO_CENTER("M"), INTERLACE_COVER("N"), COVER_UP("O"),
        COVER_DOWN("P"), SCAN_LINE("Q"), EXPLODE("R"), PAC_MAN("S"), FALL_STACK("T"),
        SHOOT("U"), FLASH("V"), RANDOM_PIXELS_ARE_ADDED_RANDOMLY("W"), SLIDE_IN("X");
        private String code;

        public String getCode() {
            return this.code;
        }
        Animations(String code) {
            this.code = code;
        }
    }

    public static enum Color {
        RED("\\a"), BRIGHT_RED("\\b"), ORANGE("\\c"), BRIGHT_ORANGE("\\d"), YELLOW("\\e"),
        BRIGHT_YELLOW("\\f"), GREEN("\\g"), BRIGHT_GREEN("\\h"), RAINBOW("\\i"),
        BRIGHT_LAYER_MIX("\\j"), VERTICAL_MIX("\\k"), SAW_TOOTH_MIX("\\l"), GREEN_ON_RED("\\m"),
        RED_ON_GREEN("\\n"), ORANGE_ON_RED("\\o"), YELLOW_ON_GREEN("\\p");

        private String code;

        public String getCode() {
            return this.code;
        }
        Color(String code) {
            this.code = code;
        }
    }

    public static enum Font {
        SHORT("\\q"), SHORT_WIDE("\\r"), DEFAULT("\\s"), WIDE("\\t"),
        SEVEN_BY_NINE("\\u"), XTRA_WIDE("\\v"), SMALL("\\w");

        private String code;
        public String getCode() {

            return this.code;
        }

        Font(String code) {
            this.code = code;

        }
    }

    public static enum Speed {
        SUPER_FAST("\\Y1"), XTRA_FAST("\\Y2"), FAST("\\Y3"), MEDIUM("\\Y4"),
        SLOW("\\Y5"), XTRA_SLOW("\\Y6"), SUPER_SLOW("\\Y7"), TOO_SLOW("\\Y8");

        private String code;
        public String getCode() {
            return this.code;
        }

        Speed(String code) {
            this.code = code;
        }
    }

    public static enum Delay {
        SUPER_FAST("\\Z1"), XTRA_FAST("\\Z2"), FAST("\\Z3"), MEDIUM("\\Z4"),
        SLOW("\\Z5"), XTRA_SLOW("\\Z6"), SUPER_SLOW("\\Z7"), SNAIL("\\Z8");

        private String code;
        public String getCode() {
            return this.code;
        }

        Delay(String code) {
            this.code = code;
        }
    }

    public GeekCatchLED(String portName, int baudRate) {
        this.initSerialPort(portName, baudRate);
    }

    private void initSerialPort(String name, int baud) {
        if (serialPort != null && serialPort.isOpen()) {
            closePort();
        }

        serialPort = SerialPort.getCommPort(name);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setNumDataBits(8);
        serialPort.addDataListener(this);
        serialPort.setBaudRate(baud);
    }

    public boolean openPort() throws Exception {
        if (serialPort == null) {
            throw new Exception("The connection wasn't initialized");
        }

        if (serialPort.isOpen()) {
            throw new Exception("Can not connect, serial port is already open");
        }

        return serialPort.openPort();
    }

    public void closePort() {
        if (serialPort != null) {
            serialPort.removeDataListener();
            serialPort.closePort();
        }
    }

    public void sendByteImmediately(byte b) {
        serialPort.writeBytes(new byte[]{b}, 1);
    }

    public void sendStringToComm(String command) {
        byte[] bCommand = command.getBytes(StandardCharsets.US_ASCII);
        serialPort.writeBytes(bCommand, bCommand.length);
    }

    public boolean isOpen() {
        return serialPort.isOpen();
    }

    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    public void serialEvent(com.fazecast.jSerialComm.SerialPortEvent event) {
        if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
            return;
        }

        byte[] newData = new byte[serialPort.bytesAvailable()];
        int numRead = serialPort.readBytes(newData, newData.length);
        System.out.printf((Arrays.toString(newData)) + "%n", numRead);
    }

    public List<String> getPortNames() {
        return Arrays.stream(SerialPort.getCommPorts())
                .map(SerialPort::getSystemPortName)
                .collect(Collectors.toList());
    }
    public static void StuffnThings(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the chatter's name: ");
        name = scanner.nextLine();
        System.out.print("Enter the donation amount: ");
            amount = scanner.nextInt();
            System.out.print("Enter the donation message: ");
            message = scanner.nextLine();
            scanner.nextLine();
    }
    public static void main(String[] args) {
        StuffnThings();

        GeekCatchLED gcl = null;
        try {
            gcl = new GeekCatchLED("tty.usbserial-1440", 9600);
            for(String p : gcl.getPortNames()) {
                System.out.println(p);
            }
            if(gcl.openPort()) {
                if (amount >= 100){
                    SourceClass Highest = new UpperClass(gcl);
                    Highest.DesignTemplate();
                }
                if (amount >= 50 && amount <= 75){
                    SourceClass HighestMiddle = new UpperMiddleClass(gcl);
                    HighestMiddle.DesignTemplate();
                } if (amount >= 10 && amount <= 49){
                    SourceClass LowerMiddle = new MiddleLowerClass(gcl);
                    LowerMiddle.DesignTemplate();
                }
                if (amount >= 1 && amount <= 9){
                    SourceClass Lowest = new LowerClass(gcl);
                    Lowest.DesignTemplate();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            assert gcl != null;
            gcl.closePort();
        }
    }
}