public class Between10and50 extends SourceClass{
    @Override
    public void DesignTemplate() throws Exception {
        GeekCatchLED gcl = null;
        gcl = new GeekCatchLED("tty.usbserial-1440", 9600);
        for(String p : gcl.getPortNames()) {
            System.out.println(p);
        }
        if(gcl.openPort()) {
            gcl.sendStringToComm(
                    "~128~f02"+
                            GeekCatchLED.Animations.OPEN_FROM_RIGHT.getCode()+
                            GeekCatchLED.Speed.SLOW.getCode()+
                            GeekCatchLED.Delay.MEDIUM.getCode()+
                            GeekCatchLED.Color.BRIGHT_YELLOW.getCode()+
                            GeekCatchLED.Font.DEFAULT.getCode()+
                            GeekCatchLED.name + "Just donated: " + GeekCatchLED.amount + "To say " + GeekCatchLED.message);
        }

    }
}
