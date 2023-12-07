public class UpperClass extends SourceClass{
    private GeekCatchLED gcl;
    public UpperClass (GeekCatchLED gcl){
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
                            GeekCatchLED.Animations.EXPLODE.getCode()+
                            GeekCatchLED.Speed.SLOW.getCode()+
                            GeekCatchLED.Delay.SLOW.getCode()+
                            GeekCatchLED.Color.BRIGHT_LAYER_MIX.getCode()+
                            GeekCatchLED.Font.SEVEN_BY_NINE.getCode()+
                            GeekCatchLED.name + "Just donated: " + GeekCatchLED.amount + "To say " + GeekCatchLED.message);
        }

    }
}
