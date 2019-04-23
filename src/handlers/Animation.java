package handlers;

import com.badlogic.gdx.graphics.Texture;

public class Animation
{
  private Texture[] frames;
  private float time;
  private float delay;
  private int currentFrame;
  
  public Animation(Texture[] frames, float delay) {
    setFrames(frames, delay);
  }
  
  public Animation(Texture[] frames) {
    this(frames, 0.0F);
  }
  
  public void setFrames(Texture[] frames, float delay) {
    this.frames = frames;
    this.delay = delay;
    time = 0.0F;
    currentFrame = 0;
  }
  
  public void update(float dt) { time += dt;
    while (time >= delay)
      step();
  }
  
  public void step() {
    time -= delay;
    currentFrame += 1;
    if (currentFrame == frames.length)
      currentFrame = 0;
  }
  
  public Texture getFrame() {
    return frames[currentFrame];
  }
}