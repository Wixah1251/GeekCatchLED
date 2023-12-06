public class LowerClass extends SourceClass{
    private GeekCatchLED gcl;
    public LowerClass (GeekCatchLED gcl){
        this.gcl = gcl;
    }
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
                            GeekCatchLED.Animations.SCROLL_UP.getCode()+
                            GeekCatchLED.Speed.SLOW.getCode()+
                            GeekCatchLED.Delay.MEDIUM.getCode()+
                            GeekCatchLED.Color.GREEN.getCode()+
                            GeekCatchLED.Font.SHORT.getCode()+
                            GeekCatchLED.name + "Just donated: " + GeekCatchLED.amount + "To say " + GeekCatchLED.message);
        }

    }
}