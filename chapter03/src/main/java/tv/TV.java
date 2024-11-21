package tv;

public class TV {
    private int channel;
    private int volume;
    private boolean power;

    public TV(int channel, int volume, boolean power) {
        this.channel = channel;
        this.volume = volume;
        this.power = power;
    }

    public TV() {
        this(1, 0, false);
    }

    public int getChannel() {
        return channel;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isPower() {
        return power;
    }

    public void power(boolean on) {
        this.power = on;
    }

    public void channel(int channel) {
        this.channel = channel > 255 ? channel - 255 : channel;
    }

    public void channel(boolean up) {
        this.channel(up?++channel:--channel);
    }

    public void volume(int volume) {
        this.volume = volume > 100 ? volume - 100 : volume;
    }

    public void volume(boolean up) {
        this.volume(up?++volume:--volume);
    }

    public void status() {
        System.out.printf("TV[channel=%s, volume=%s, power=%s]\n", channel, volume, power ? "ON" : "OFF");
    }
}
