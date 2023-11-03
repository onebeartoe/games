/* Centipedo.java - based on the arcade game Centipede. */

/* 
 * Copyright (C) 1996 Mark Boyns <boyns@sdsu.edu>
 *
 * Centipedo <URL:http://www.sdsu.edu/~boyns/java/centipedo/>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/**
 * Centipedo is a fast-paced action game based on the arcade game
 * Centipede.
 *
 * @version 1.0 26 Mar 1996
 * @author Mark Boyns
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.Polygon;

/**
 * Mushroom - extend Point to keep track of damage.
 */
class Mushroom extends Point
{
    int damage = 0;

    Mushroom (int x, int y)
    {
	super (x, y);
    }
}

/**
 * Centipede - extend Point to keep track of vertical and horizontal
 * direction.
 */
class Centipede extends Point
{
    int hort_dir = 0;
    int vert_dir = 0;
    
    Centipede (int x, int y, int hort_dir, int vert_dir)
    {
	super (x, y);
	this.hort_dir = hort_dir;
	this.vert_dir = vert_dir;
    }
}

/**
 * Scorpion - extend Point to keep track of horizontal direction.
 */
class Scorpion extends Point
{
    int hort_dir = 0;

    Scorpion (int x, int y, int hort_dir)
    {
	super (x, y);
	this.hort_dir = hort_dir;
    }
}

/**
 * Beetle - extend Point to keep track of horizontal direction.
 */
class Beetle extends Point
{
    int hort_dir = 0;

    Beetle (int x, int y, int hort_dir)
    {
	super (x, y);
	this.hort_dir = hort_dir;
    }
}

/**
 * Spider - extend Point to keep track of vertical and horizontal
 * direction and starting direction.
 */
class Spider extends Point
{
    int hort_dir = 0;
    int vert_dir = 0;
    int start_dir = 0;
    
    Spider (int x, int y, int hort_dir, int vert_dir)
    {
	super (x, y);
	this.hort_dir = hort_dir;
	this.vert_dir = vert_dir;
    }
}

/**
 * Centipdo - everything else
 */
public class centipedo extends java.applet.Applet implements Runnable
{
    /**
     * Screen sizes.
     */
    final int screen_width = 600;
    final int field_height = 416;
    final int stats_height = 32;

    /**
     * Points
     */
    final int mushroom_points = 5;
    final int centipede_points = 10;
    final int beetle_points = 20;
    final int flea_points = 50;
    final int spider_points = 75;
    final int scorpion_points = 500;
    final int extra_life_points = 4000;

    /**
     * MediaTracker IDs used for image loading.
     */
    final int tracker_player_id = 0;
    final int tracker_shot_id = 1;
    final int tracker_mushroom_id = 2;
    final int tracker_centipede_id = 3;
    final int tracker_beetle_id = 4;
    final int tracker_scorpion_id = 5;
    final int tracker_spider_id = 6;
    final int tracker_flea_id = 6;

    /**
     * Colors
     */
    final Color missing_image_color = Color.black;
    final Color background_colors[] = 
    {
	Color.white,
	Color.cyan,
	Color.yellow,
	Color.orange,
	Color.green,
	Color.pink,
    };
    
    /**
     * Fonts
     */
    Font big_font;
    FontMetrics big_font_metrics;

    /**
     * MediaTracker and Images
     */
    MediaTracker tracker;
    Image player_image;
    Image dead_player_image;
    Image shot_image;
    Image mushroom_image;
    Image centipede_images[];
    Image beetle_images[];
    Image scorpion_images[];
    Image spider_image;
    Image flea_image;

    /**
     * Sounds
     */
    AudioClip fire_shot_sound = null;
    AudioClip destroy_mushroom_sound = null;
    AudioClip destroy_centipede_sound = null;
    AudioClip destroy_beetle_sound = null;
    AudioClip destroy_scorpion_sound = null;
    AudioClip destroy_spider_sound = null;
    AudioClip destroy_flea_sound = null;
    AudioClip destroy_player_sound = null;
    AudioClip new_level_sound = null;
    AudioClip extra_life_sound = null;

    /**
     * Status flags
     */
    boolean game_over = true;
    boolean clear_screen = false;
    boolean mouse_down = false;
    boolean need_reset = false;
    boolean update_mushrooms = true;
    boolean update_level = true;
    boolean update_score = true;
    boolean update_lives = true;
    boolean update_player = true;
    boolean expand_mushrooms = false;
    boolean paused = false;

    /**
     * The mouse
     */
    Point mouse;
    Point mouse_loc;

    /**
     * The creatures
     */
    Beetle beetles[] = null;
    Centipede centipede[] = null;
    Point fleas[] = null;
    Scorpion scorpions[] = null;
    Spider spiders[] = null;

    /**
     * Mushrooms
     */
    Mushroom mushrooms[] = null;

    /**
     * Shots
     */
    Point shots[] = null;

    /**
     * The game thread.
     */
    Thread thread = null;

    /**
     * Misc. game parameters.
     */
    int centipede_length = 12;
    int max_shots = 5;
    int max_mushrooms = 40;
    int max_beetles = 0;
    int max_scorpions = 0;
    int max_spiders = 0;
    int max_fleas = 0;
    int max_creature_count = 100;
    int shot_speed = 40;
    int mushroom_count = 0;
    int lives = 0;
    int level = 0;
    int score = 0;
    int lscore = 0;
    
    public void init ()
    {
	int i;

	/* Load the font */
	big_font = new Font ("TimesRoman", Font.BOLD, 24);
	big_font_metrics = getFontMetrics (big_font);
	setFont (big_font);

	/* Load all the images using MediaTracker. */
	tracker = new MediaTracker (this);
	
	player_image = getImage (getCodeBase (), "images/player.gif");
	tracker.addImage (player_image, tracker_player_id);

	dead_player_image = getImage (getCodeBase (), "images/dead_player.gif");
	tracker.addImage (dead_player_image, tracker_player_id);

	shot_image = getImage (getCodeBase (), "images/shot.gif");
	tracker.addImage (shot_image, tracker_shot_id);

	mushroom_image = getImage (getCodeBase (), "images/mushroom.gif");
	tracker.addImage (mushroom_image, tracker_mushroom_id);

	beetle_images = new Image[2];
	beetle_images[0] = getImage (getCodeBase (), "images/beetle_right.gif");
	beetle_images[1] = getImage (getCodeBase (), "images/beetle_left.gif");
	tracker.addImage (beetle_images[0], tracker_beetle_id);
	tracker.addImage (beetle_images[1], tracker_beetle_id);

	scorpion_images = new Image[2];
	scorpion_images[0] = getImage (getCodeBase (), "images/scorpion_right.gif");
	scorpion_images[1] = getImage (getCodeBase (), "images/scorpion_left.gif");
	tracker.addImage (scorpion_images[0], tracker_scorpion_id);
	tracker.addImage (scorpion_images[1], tracker_scorpion_id);

	spider_image = getImage (getCodeBase (), "images/spider.gif");
	tracker.addImage (spider_image, tracker_spider_id);
	
	flea_image = getImage (getCodeBase (), "images/flea.gif");
	tracker.addImage (flea_image, tracker_flea_id);
	
	centipede_images = new Image[2];
	centipede_images[0] = getImage (getCodeBase (), "images/head.gif");
	centipede_images[1] = getImage (getCodeBase (), "images/body.gif");
	tracker.addImage (centipede_images[0], tracker_centipede_id);
	tracker.addImage (centipede_images[1], tracker_centipede_id);

	/* Load all the sounds. */
	fire_shot_sound = getAudioClip (getCodeBase (), "sounds/shot.au");
	destroy_mushroom_sound = getAudioClip (getCodeBase (), "sounds/drip.au");
	destroy_centipede_sound = getAudioClip (getCodeBase (), "sounds/Water.au");
	destroy_beetle_sound = getAudioClip (getCodeBase (), "sounds/crunch.au");
	destroy_scorpion_sound = getAudioClip (getCodeBase (), "sounds/wohoo.au");
	destroy_spider_sound = getAudioClip (getCodeBase (), "sounds/hammer.au");
	destroy_flea_sound = getAudioClip (getCodeBase (), "sounds/hehehehe.au");
	destroy_player_sound = getAudioClip (getCodeBase (), "sounds/crap1.au");
	new_level_sound = getAudioClip (getCodeBase (), "sounds/magic.au");
	extra_life_sound = getAudioClip (getCodeBase (), "sounds/bleep.au");

	/* Create the array of player shots.  Only needs to be done once. */
	shots = new Point[max_shots];
	for (i = 0; i < shots.length; i++)
	{
	    shots[i] = new Point (0, 0);
	}

	/* Mouse */
	mouse_loc = new Point (screen_width/2, field_height/2);
	mouse = new Point (screen_width/2, field_height/2);

	setBackground (background_colors[0]);
	resize (screen_width, field_height + stats_height);

	/* Start the game! */
	thread = new Thread (this);
	thread.start ();
    }

    public void start ()
    {
	if (thread == null)
	{
	    thread = new Thread (this);
	    thread.start ();
	}
    }

    public void stop ()
    {
	if (thread != null && thread.isAlive ())
	{
	    thread.stop ();
	    thread = null;
	}
    }

    public void run ()
    {
	int i;
	int tick;
	double chance;
	boolean have_mushroom_image = false;

	/* Start loading the images */
	tracker.checkAll (true);

	/* Demo settings */
	max_beetles = 3;
	max_scorpions = 1;
	max_spiders = 1;
	max_fleas = 3;
    	reset_creatures ();
	generate_centipede ();
	generate_mushrooms ();

	for (;;)
	{
	    /* Randomly generate creatures. */
	    chance = Math.random ();
	    if (chance < 0.50)
	    {
		generate_beetle ();
	    }
	    if (chance < 0.30)
	    {
		generate_spider ();
	    }
	    if (chance < 0.05)
	    {
		generate_scorpion ();
	    }

	    /* Generate fleas to generate mushrooms. */
	    if (mushroom_count < max_mushrooms)
	    {
		generate_flea ();
	    }

	    /* Fire player shots */
	    if (mouse_down)
	    {
		fire_shot (mouse_loc.x, mouse_loc.y);
	    }

	    if (!have_mushroom_image)
	    {
		have_mushroom_image = tracker.checkID (tracker_mushroom_id, true);
		if (have_mushroom_image)
		{
		    clear_screen = true;
		    update_mushrooms = true;
		    update_lives = true;
		    update_score = true;
		    update_level = true;
		}
	    }
	    
	    /* Update the screen. */
	    repaint ();

	    /* Sleep for 1/10 a second. */
	    try
	    {
		Thread.sleep (100);
	    }
	    catch (Exception e)
	    {
		return;
	    }
	}
    }

    void start_game ()
    {
	lives = 3;
	level = 1;
	score = 0;
	lscore = 0;
	max_beetles = 1;
	max_scorpions = 0;
	max_spiders = 1;
	max_fleas = 1;
	game_over = false;
	clear_screen = true;
	setBackground (background_colors[(level-1) % background_colors.length]);
	update_lives = true;
	update_score = true;
	update_level = true;
	reset_creatures ();
	generate_mushrooms ();
	generate_centipede ();
    }

    void reset_creatures ()
    {
	int i;

	beetles = new Beetle[Math.min (max_beetles, max_creature_count)];
	for (i = 0; i < beetles.length; i++)
	{
	    beetles[i] = new Beetle (0, 0, 0);
	}

	scorpions = new Scorpion[Math.min (max_scorpions, max_creature_count)];
	for (i = 0; i < scorpions.length; i++)
	{
	    scorpions[i] = new Scorpion (0, 0, 0);
	}

	spiders = new Spider[Math.min (max_spiders, max_creature_count)];
	for (i = 0; i < spiders.length; i++)
	{
	    spiders[i] = new Spider (0, 0, 0, 0);
	}

	fleas = new Point[Math.min (max_fleas, max_creature_count)];
	for (i = 0; i < fleas.length; i++)
	{
	    fleas[i] = new Point (0, 0);
	}
    }
    
    void reset_game ()
    {
	int i;

	for (i = 0; i < shots.length; i++)
	{
	    shots[i].x = 0;
	    shots[i].y = 0;
	}

	if (centipede != null)
	{
	    generate_centipede ();
	}

	clear_screen = true;
	update_mushrooms = true;
	update_lives = true;
	update_score = true;
	update_level = true;
	reset_creatures ();
    }

    void new_level ()
    {
	if (new_level_sound != null)
	{
	    new_level_sound.play ();
	}
	
	level++;

	setBackground (background_colors[(level-1) % background_colors.length]);
	clear_screen = true;
	update_mushrooms = true;
	update_lives = true;
	update_score = true;
	update_level = true;
	update_player = true;

	generate_centipede ();

	if ((level % 3) == 0)
	{
	    if (max_scorpions == 0)
	    {
		max_scorpions = Math.min (max_scorpions + 1, max_creature_count);
	    }
	    max_beetles = Math.min (max_beetles + 1, max_creature_count);
	    max_fleas = Math.min (max_fleas + 1, max_creature_count);
	}
	if ((level % 5) == 0)
	{
	    max_spiders = Math.min (max_spiders + 1, max_creature_count);
	    max_scorpions = Math.min (max_scorpions + 1, max_creature_count);
	}

	reset_creatures ();
    }

    void suspend_game ()
    {
	if (thread != null && !game_over)
	{
	    thread.suspend ();
	    paused = true;
	    clear_screen = true;
	    repaint ();
	}
    }

    void resume_game ()
    {
	if (thread != null && !game_over)
	{
	    thread.resume ();
	    paused = false;
	    clear_screen = true;
	    update_mushrooms = true;
	    update_lives = true;
	    update_score = true;
	    update_level = true;
	    update_player = true;
	}
    }
    
    /* Handle mouse up events. */
    public boolean mouseUp (Event e, int x, int y)
    {
	if (game_over)
	{
	     start_game ();
	}
	mouse_down = false;
	return true;
    }
    
    /* Handle mouse down events. */
    public boolean mouseDown (Event e, int x, int y)
    {
	mouse_down = true;
	return true;
    }

    void move_mouse (int x, int y)
    {
	int min_y = field_height - field_height/3;
	if (y < min_y)
	{
	    y = min_y;
	}
	else if (y > field_height - 16)
	{
	    y = field_height - 16;
	}
	mouse_loc.move (x, y);
    }
    
    /* Handle mouse move events. */
    public boolean mouseMove (Event e, int x, int y)
    {
	move_mouse (x, y);
	return true;
    }

    /* Handle mouse drag events. */
    public boolean mouseDrag (Event e, int x, int y)
    {
	move_mouse (x, y);
	return true;
    }
    
    /* Handle keyboard events. */
    public boolean keyDown (Event e, int key)
    {
	switch (key)
	{
	case 'g':
	case 'G':
	    if (!paused)
	    {
		start_game ();
	    }
	    break;

	case 'p':
	case 'P':
	    if (paused)
	    {
		resume_game ();
	    }
	    else
	    {
		suspend_game ();
	    }
	    break;
	    
	case 'x':
	    new_level ();
	    break;
	}
	
	return true;
    }

    /* Handle mouse enter events. */
    public boolean mouseEnter (Event e, int x, int y)
    {
	if (!game_over)
	{
	    resume_game ();
	}
	return true;
    }

    /* Handle mouse exit events. */
    public boolean mouseExit (Event e, int x, int y)
    {
	if (!game_over)
	{
	    suspend_game ();
	}
	return true;
    }

    boolean collision (Point p1, int r1, Point p2, int r2)
    {
	int dist = (int) Math.sqrt (((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
	int range = r1 + r2;
	//System.out.println ("dist = " + dist + " range = " + range);
	return dist <= range;
    }

    void fire_shot (int x, int y)
    {
	int i;

	if (game_over)
	{
	    return;
	}

	for (i = 0; i < shots.length; i++)
	{
	    if (shots[i].y == 0)
	    {
		shots[i].x = x;
		shots[i].y = y;
		break;
	    }
	}

	if (i < shots.length)
	{
	    if (fire_shot_sound != null)
	    {
		fire_shot_sound.play ();
	    }
	}
    }

    void increment_score (int points)
    {
	score += points;
	lscore += points;
	if (lscore >= extra_life_points)
	{
	    if (extra_life_sound != null)
	    {
		extra_life_sound.play ();
	    }
	    lscore -= extra_life_points;
	    lives++;
	    update_lives = true;
	}
	update_score = true;
    }

    void destroy_mushroom (Graphics g, int i)
    {
	increment_score (mushroom_points);

	if (destroy_mushroom_sound != null)
	{
	    destroy_mushroom_sound.play ();
	}
	
	mushrooms[i].damage++;
	if (mushrooms[i].damage < 3)
	{
	    g.clearRect (mushrooms[i].x - 16, mushrooms[i].y - 16, 32, 32);
	    paint_mushroom_collision (g, mushrooms[i], 16);
	    return;
	}
	
	Point p = new Point (mushrooms[i].x, mushrooms[i].y);

	g.clearRect (mushrooms[i].x - 16, mushrooms[i].y - 16, 32, 32);

	mushrooms[i].x = 0;
	mushrooms[i].y = 0;
	mushroom_count--;

	paint_mushroom_collision (g, p, 16);
    }

    void destroy_centipede (Graphics g, int i)
    {
	Point p = new Point (centipede[i].x, centipede[i].y);
	int j;

	increment_score (centipede_points);

	if (destroy_centipede_sound != null)
	{
	    destroy_centipede_sound.play ();
	}
	
	g.clearRect (centipede[i].x - 8, centipede[i].y - 8, 16, 16);

	centipede[i].x = 0;
	centipede[i].y = 0;

	j = generate_mushroom (p.x, p.y);
	if (j != -1)
	{
	    paint_mushroom (g, j);
	}

	for (j = 0; j < centipede.length; j++)
	{
	    if (centipede[j].x != 0 && centipede[j].y != 0)
	    {
		break;
	    }
	}
	if (j == centipede.length)
	{
	    new_level ();
	}
	
	paint_mushroom_collision (g, p, 8);
    }

    void destroy_spider (Graphics g, int i)
    {
	Point p = new Point (spiders[i].x, spiders[i].y);

	if (destroy_spider_sound != null)
	{
	    destroy_spider_sound.play ();
	}
	
	increment_score (spider_points);
	
	g.clearRect (spiders[i].x - 16, spiders[i].y - 16, 32, 32);

	spiders[i].x = 0;
	spiders[i].y = 0;

	paint_mushroom_collision (g, p, 16);
    }

    void destroy_scorpion (Graphics g, int i)
    {
	Point p = new Point (scorpions[i].x, scorpions[i].y);

	if (destroy_scorpion_sound != null)
	{
	    destroy_scorpion_sound.play ();
	}
	
	increment_score (scorpion_points);
	
	g.clearRect (scorpions[i].x - 16, scorpions[i].y - 16, 32, 32);

	scorpions[i].x = 0;
	scorpions[i].y = 0;

	paint_mushroom_collision (g, p, 16);
    }

    void destroy_beetle (Graphics g, int i)
    {
	Point p = new Point (beetles[i].x, beetles[i].y);

	if (destroy_beetle_sound != null)
	{
	    destroy_beetle_sound.play ();
	}
	
	increment_score (beetle_points);
	
	g.clearRect (beetles[i].x - 16, beetles[i].y - 16, 32, 32);

	beetles[i].x = 0;
	beetles[i].y = 0;

	paint_mushroom_collision (g, p, 16);
    }

    void destroy_flea (Graphics g, int i)
    {
	Point p = new Point (fleas[i].x, fleas[i].y);

	if (destroy_flea_sound != null)
	{
	    destroy_flea_sound.play ();
	}
	
	increment_score (flea_points);
	
	g.clearRect (fleas[i].x - 8, fleas[i].y - 8, 16, 16);

	fleas[i].x = 0;
	fleas[i].y = 0;

	paint_mushroom_collision (g, p, 8);
    }

    void destroy_player ()
    {
	if (game_over)
	{
	    return;
	}
	
	if (destroy_player_sound != null)
	{
	    destroy_player_sound.play ();
	}
	
	if (need_reset)
	{
	    return;
	}
	
	lives--;
	if (lives < 0)
	{
	    lives = 0;
	    game_over = true;
	}
	else
	{
	    need_reset = true;
	    expand_mushrooms = true;
	}
	
	update_lives = true;
    }

    boolean check_player_collision (Point p, int r)
    {
	boolean hit = collision (p, r, mouse_loc, 16);
	if (hit)
	{
	    destroy_player ();
	    update_player = true;
	}
	return hit;
    }

    int generate_mushroom (int x, int y)
    {
	int j;

	if (y > field_height - 16)
	{
	    y = field_height - 16;
	}
	
	for (j = 0; j < mushrooms.length; j++)
	{
	    if (mushrooms[j].x == 0 && mushrooms[j].y == 0)
	    {
		mushrooms[j].x = x;
		mushrooms[j].y = y;
		mushrooms[j].damage = 0;
		mushroom_count++;
		return j;
	    }
	}
	return -1;
    }

    void generate_mushrooms ()
    {
	int i;
	int size = max_mushrooms + (centipede_length * 3);

	mushrooms = new Mushroom[size];
	for (i = 0; i < max_mushrooms; i++)
	{
	    mushrooms[i] = new Mushroom ((int)(Math.random () * screen_width),
					 (int)(Math.random () * (field_height - 16)));
	}
	mushroom_count = max_mushrooms;
	for (; i < size; i++)
	{
	    mushrooms[i] = new Mushroom (0, 0);
	}
	
	update_mushrooms = true;
    }

    void generate_centipede ()
    {
	int i;
	int x, y, h;

	if (Math.random () > 0.50)
	{
	    x = screen_width - 8;
	    h = -1;
	}
	else
	{
	    x = 8;
	    h = 1;
	}
	
	centipede = new Centipede[centipede_length];
	for (i = 0; i < centipede.length; i++)
	{
	    if (i == 0)
	    {
		centipede[i] = new Centipede (x, 8, h, 1);
	    }
	    else
	    {
		centipede[i] = new Centipede (centipede[i-1].x + -(h*16),
					      centipede[i-1].y, h, 1);
	    }
	}
    }

    void generate_beetle ()
    {
	int i;

	for (i = 0; i < max_beetles; i++)
	{
	    if (beetles[i].x == 0 && beetles[i].y == 0)
	    {
		if (Math.random () > 0.50)
		{
		    beetles[i].hort_dir = 1;
		    beetles[i].x = 8;
		}
		else
		{
		    beetles[i].hort_dir = -1;
		    beetles[i].x = screen_width - 8;
		}
		beetles[i].y = field_height/2 + (int)(Math.random () * (field_height/2 - 16));
		break;
	    }
	}
    }

    void generate_scorpion ()
    {
	int i;

	for (i = 0; i < max_scorpions; i++)
	{
	    if (scorpions[i].x == 0 && scorpions[i].y == 0)
	    {
		if (Math.random () > 0.50)
		{
		    scorpions[i].hort_dir = 1;
		    scorpions[i].x = 16;
		}
		else
		{
		    scorpions[i].hort_dir = -1;
		    scorpions[i].x = screen_width - 16;
		}
		scorpions[i].y = 100;
		break;
	    }
	}
    }

    void generate_spider ()
    {
	int i;

	for (i = 0; i < max_spiders; i++)
	{
	    if (spiders[i].x == 0 && spiders[i].y == 0)
	    {
		if (Math.random () > 0.50)
		{
		    spiders[i].x = 8;
		    spiders[i].hort_dir = 1;
		}
		else
		{
		    spiders[i].x = screen_width - 8;
		    spiders[i].hort_dir = -1;
		}
		spiders[i].y = field_height/2 + (int)(Math.random () * (field_height/2)) - 16;
		spiders[i].vert_dir = 1;
		spiders[i].start_dir = spiders[i].hort_dir;
		break;
	    }
	}
    }

    void generate_flea ()
    {
	int i;

	for (i = 0; i < max_fleas; i++)
	{
	    if (fleas[i].x == 0 && fleas[i].y == 0)
	    {
		fleas[i].x = (int)(Math.random () * screen_width);
		fleas[i].y = 8;
		break;
	    }
	}
    }
    
    /* Don't clear the screen; just call paint. */
    public void update (Graphics g)
    {
	paint (g);
    }

    void paint_mushroom (Graphics g, int i)
    {
	boolean have_image = tracker.checkID (tracker_mushroom_id, true);

	if (have_image)
	{
	    int r = 16 - (mushrooms[i].damage * 4);
	    g.drawImage (mushroom_image,
			 mushrooms[i].x - r, mushrooms[i].y - r,
			 r * 2, r * 2,
			 this);
	}
	else
	{
	    Polygon p = new Polygon ();
	    p.addPoint (mushrooms[i].x - 16, mushrooms[i].y + 16);
	    p.addPoint (mushrooms[i].x, mushrooms[i].y - 16);
	    p.addPoint (mushrooms[i].x + 16, mushrooms[i].y + 16);
	    g.setColor (missing_image_color);
	    g.fillPolygon (p);
	}
    }
    
    boolean paint_mushroom_collision (Graphics g, Point p, int r)
    {
	int i;
	boolean hit = false;
	
	for (i = 0; i < mushrooms.length; i++)
	{
	    if (mushrooms[i].x == 0 && mushrooms[i].y == 0)
	    {
		continue;
	    }
	    
	    if (collision (p, r, mushrooms[i], 16))
	    {
		paint_mushroom (g, i);
		hit = true;
	    }
	}

	return hit;
    }
    
    void paint_mushrooms (Graphics g)
    {
	int i;
	
    	for (i = 0; i < mushrooms.length; i++)
	{
	    if (mushrooms[i].x == 0 && mushrooms[i].y == 0)
	    {
		continue;
	    }
	    paint_mushroom (g, i);
	}
    }

    void paint_mushrooms_expand (Graphics g)
    {
	int i;
	
    	for (i = 0; i < mushrooms.length; i++)
	{
	    if (mushrooms[i].x == 0 && mushrooms[i].y == 0)
	    {
		continue;
	    }

	    if (mushrooms[i].damage > 0)
	    {
		mushrooms[i].damage = 0;
		paint_mushroom (g, i);
		if (destroy_mushroom_sound != null)
		{
		    destroy_mushroom_sound.play ();
		}
	    }
	}
    }
    
    void paint_player (Graphics g)
    {
	if (!update_player && mouse.x == mouse_loc.x && mouse.y == mouse_loc.y)
	{
	    return;
	}
	update_player = false;

	boolean have_image = tracker.checkID (tracker_player_id, true);

	g.clearRect (mouse.x - 16, mouse.y - 16, 32, 32);

	paint_mushroom_collision (g, mouse, 16);

	mouse.x = mouse_loc.x;
	mouse.y = mouse_loc.y;

	paint_mushroom_collision (g, mouse, 16);

	if (have_image)
	{
	    if (game_over)
	    {
		g.drawImage (dead_player_image, mouse.x - 16, mouse.y - 16, this);
	    }
	    else
	    {
		g.drawImage (player_image, mouse.x - 16, mouse.y - 16, this);
	    }
	}
	else
	{
	    g.setColor (missing_image_color);
	    g.fillOval (mouse.x - 16, mouse.y - 16, 32, 32);
	}
    }

    void paint_shots (Graphics g)
    {
	int i;
	int j;
	boolean have_image = tracker.checkID (tracker_shot_id, true);
	boolean hit;

	for (i = 0; i < shots.length; i++)
	{
	    if (shots[i].x == 0 && shots[i].y == 0)
	    {
		continue;
	    }
	    
	    g.clearRect (shots[i].x - 8, shots[i].y - 8, 16, 16);

	    hit = false;

	    for (j = 0; !hit && j < centipede.length; j++)
	    {
		if (collision (shots[i], 8, centipede[j], 8))
		{
		    hit = true;
		    destroy_centipede (g, j);
		}
	    }

	    for (j = 0; !hit && j < max_spiders; j++)
	    {
		if (collision (shots[i], 8, spiders[j], 16))
		{
		    hit = true;
		    destroy_spider (g, j);
		}
	    }

	    for (j = 0; !hit && j < max_scorpions; j++)
	    {
		if (collision (shots[i], 8, scorpions[j], 16))
		{
		    hit = true;
		    destroy_scorpion (g, j);
		    break;
		}
	    }

	    for (j = 0; !hit && j < max_beetles; j++)
	    {
		if (collision (shots[i], 8, beetles[j], 16))
		{
		    hit = true;
		    destroy_beetle (g, j);
		}
	    }

	    for (j = 0; !hit && j < max_fleas; j++)
	    {
		if (collision (shots[i], 8, fleas[j], 16))
		{
		    hit = true;
		    destroy_flea (g, j);
		}
	    }

	    for (j = 0; !hit && j < mushrooms.length; j++)
	    {
		if (collision (shots[i], 8, mushrooms[j], 16))
		{
		    hit = true;
		    destroy_mushroom (g, j);
		}
	    }

	    if (hit)
	    {
		shots[i].x = 0;
		shots[i].y = 0;
		continue;
	    }
		    
	    shots[i].y -= shot_speed;

	    if (shots[i].y <= 0)
	    {
		shots[i].x = 0;
		shots[i].y = 0;
		continue;
	    }
		
	    if (have_image)
	    {
		g.drawImage (shot_image, shots[i].x - 8, shots[i].y - 8, this);
	    }
	    else
	    {
		g.setColor (missing_image_color);
		g.fillOval (shots[i].x - 8, shots[i].y - 8, 16, 16);
	    }
	}
    }

    void paint_centipede (Graphics g)
    {
	int i;
	boolean have_images = tracker.checkID (tracker_centipede_id, true);
	boolean hit;
	boolean update_vert;
	
	for (i = 0; i < centipede.length; i++)
	{
	    if (centipede[i].x == 0 && centipede[i].y == 0)
	    {
		continue;
	    }

	    g.clearRect (centipede[i].x - 8, centipede[i].y - 8, 16, 16);
	    
	    /* Use 0 radius to allow centipede to get closer to mushrooms */
	    hit = paint_mushroom_collision (g, centipede[i], 0);

	    centipede[i].x += 8 * centipede[i].hort_dir;

	    update_vert = false;
	    
	    if (hit)
	    {
		centipede[i].hort_dir *= -1;
		centipede[i].x += 16 * centipede[i].hort_dir;
		//centipede[i].y += 16 * centipede[i].vert_dir;
		update_vert = true;
	    }
	    else if (centipede[i].hort_dir > 0 && centipede[i].x >= screen_width - 8)
	    {
		centipede[i].hort_dir = -1;
		centipede[i].x = screen_width - 8;
		//centipede[i].y += 16 * centipede[i].vert_dir;
		update_vert = true;
	    }
	    else if (centipede[i].hort_dir < 0 && centipede[i].x <= 8)
	    {
		centipede[i].x = 8;
		centipede[i].hort_dir = 1;
		//centipede[i].y += 16 * centipede[i].vert_dir;
		update_vert = true;
	    }

	    if (update_vert)
	    {
		centipede[i].y += 16 * centipede[i].vert_dir;
		if (centipede[i].y < 8 || centipede[i].y > (field_height - 8))
		{
		    centipede[i].vert_dir *= -1;
		    centipede[i].y += 32 * centipede[i].vert_dir;
		}
	    }

	    if (have_images)
	    {
		/* head */
		if (i == 0 || (centipede[i-1].x == 0 && centipede[i-1].y == 0))
		{
		    g.drawImage (centipede_images[0],
				 centipede[i].x - 8, centipede[i].y - 8, this);
		}
		/* body */
		else
		{
		    g.drawImage (centipede_images[1],
				 centipede[i].x - 8, centipede[i].y - 8, this);
		}
	    }
	    else
	    {
		g.setColor (missing_image_color);
		g.fillOval (centipede[i].x - 8, centipede[i].y - 8, 16, 16);
	    }

	    check_player_collision (centipede[i], 8);
	}
    }

    void paint_beetles (Graphics g)
    {
	int i;
	boolean have_image = tracker.checkID (tracker_beetle_id, true);

	for (i = 0; i < max_beetles; i++)
	{
	    if (beetles[i].x == 0 && beetles[i].y == 0)
	    {
		continue;
	    }
	    
	    g.clearRect (beetles[i].x - 8, beetles[i].y - 8, 16, 16);

	    paint_mushroom_collision (g, beetles[i], 8);

	    if (Math.random () > 0.75)
	    {
		beetles[i].x += beetles[i].hort_dir * 32;
	    }

	    if (beetles[i].x < 0 || beetles[i].x > screen_width)
	    {
		beetles[i].x = 0;
		beetles[i].y = 0;
		continue;
	    }
		
	    if (have_image)
	    {
		if (beetles[i].hort_dir == 1)
		{
		    g.drawImage (beetle_images[0], beetles[i].x - 8, beetles[i].y - 8, this);
		}
		else
		{
		    g.drawImage (beetle_images[1], beetles[i].x - 8, beetles[i].y - 8, this);
		}
	    }
	    else
	    {
		g.setColor (missing_image_color);
		g.fillOval (beetles[i].x - 8, beetles[i].y - 8, 16, 16);
	    }

	    check_player_collision (beetles[i], 8);
	}
    }

    void paint_scorpions (Graphics g)
    {
	int i;
	boolean have_image = tracker.checkID (tracker_scorpion_id, true);

	for (i = 0; i < max_scorpions; i++)
	{
	    if (scorpions[i].x == 0 && scorpions[i].y == 0)
	    {
		continue;
	    }
	    
	    g.clearRect (scorpions[i].x - 16, scorpions[i].y - 16, 32, 32);

	    paint_mushroom_collision (g, scorpions[i], 16);
	
	    scorpions[i].x += scorpions[i].hort_dir * 32;

	    if (scorpions[i].x < 0 || scorpions[i].x > screen_width)
	    {
		scorpions[i].x = 0;
		scorpions[i].y = 0;
		continue;
	    }
		
	    if (have_image)
	    {
		if (scorpions[i].hort_dir == 1)
		{
		    g.drawImage (scorpion_images[0], scorpions[i].x - 16, scorpions[i].y - 16, this);
		}
		else
		{
		    g.drawImage (scorpion_images[1], scorpions[i].x - 16, scorpions[i].y - 16, this);
		}
	    }
	    else
	    {
		g.setColor (missing_image_color);
		g.fillOval (scorpions[i].x - 16, scorpions[i].y - 16, 32, 32);
	    }

	    check_player_collision (scorpions[i], 16);
	}
    }

    void paint_spiders (Graphics g)
    {
	int i;
	boolean have_image = tracker.checkID (tracker_spider_id, true);
	double chance = Math.random ();
	int speed = 16;

	for (i = 0; i < max_spiders; i++)
	{
	    if (spiders[i].x == 0 && spiders[i].y == 0)
	    {
		continue;
	    }

	    g.clearRect (spiders[i].x - 16, spiders[i].y - 16, 32, 32);

	    paint_mushroom_collision (g, spiders[i], 16);

	    if (spiders[i].y < field_height/2 || spiders[i].y > field_height - 16)
	    {
		spiders[i].vert_dir *= -1;
		spiders[i].y += spiders[i].vert_dir * speed * 2;
	    }
	    else if (chance < 0.10)
	    {
		spiders[i].hort_dir *= -1;
		spiders[i].x += spiders[i].hort_dir * speed;
	    }
	    else if (chance > 0.90)
	    {
		spiders[i].vert_dir *= -1;
		spiders[i].y += spiders[i].vert_dir * speed;
	    }
	    else
	    {
		spiders[i].x += spiders[i].hort_dir * speed;
	    }

	    if (spiders[i].x < 16 || spiders[i].x > screen_width - 16)
	    {
		if (spiders[i].hort_dir == spiders[i].start_dir)
		{
		    spiders[i].hort_dir *= -1;
		    spiders[i].x += spiders[i].hort_dir * speed * 2;
		}
		else /* spider is gone */
		{
		    spiders[i].x = 0;
		    spiders[i].y = 0;
		    continue;
		}
	    }
		
	    if (have_image)
	    {
		g.drawImage (spider_image, spiders[i].x - 16, spiders[i].y - 16, this);
	    }
	    else
	    {
		g.setColor (missing_image_color);
		g.fillOval (spiders[i].x - 16, spiders[i].y - 16, 32, 32);
	    }
	    
	    check_player_collision (spiders[i], 16);
	}
    }

    void paint_fleas (Graphics g)
    {
	int i;
	boolean have_image = tracker.checkID (tracker_flea_id, true);

	for (i = 0; i < max_fleas; i++)
	{
	    if (fleas[i].x == 0 && fleas[i].y == 0)
	    {
		continue;
	    }
	    
	    g.clearRect (fleas[i].x - 8, fleas[i].y - 8, 16, 16);

	    paint_mushroom_collision (g, fleas[i], 8);

	    fleas[i].y += 16;

	    if (fleas[i].y > field_height - 8)
	    {
		fleas[i].x = 0;
		fleas[i].y = 0;
		continue;
	    }

	    if (Math.random () < 0.20)
	    {
		int j = generate_mushroom (fleas[i].x, fleas[i].y);
		if (j != -1)
		{
		    paint_mushroom (g, j);
		}
	    }
		
	    if (have_image)
	    {
		g.drawImage (flea_image, fleas[i].x - 8, fleas[i].y - 8, this);
	    }
	    else
	    {
		g.setColor (missing_image_color);
		g.fillOval (fleas[i].x - 8, fleas[i].y - 8, 16, 16);
	    }

	    check_player_collision (fleas[i], 8);
	}
    }

    void paint_string (Graphics g, String s)
    {
	int h = big_font_metrics.getHeight ();
	int w = big_font_metrics.stringWidth (s);
	g.setColor (Color.black);
	g.drawString (s, screen_width/2 - w/2, field_height/2);
    }
    
    void paint_game_over (Graphics g)
    {
	paint_string (g, "GAME OVER");
    }

    void paint_paused (Graphics g)
    {
	paint_string (g, "PAUSED");
    }

    void paint_score_and_level (Graphics g)
    {
	g.clearRect (0, field_height, screen_width/2, stats_height);
		     
	g.setColor (Color.black);
	g.drawLine (0, field_height, screen_width, field_height);

	g.drawString ("Score: " + score + "  Level: " + level,
		      0,
		      field_height + big_font_metrics.getHeight ());
    }

    void paint_lives (Graphics g)
    {
	int x, y;

	g.clearRect (screen_width/2, field_height, screen_width, stats_height);
	
	g.setColor (Color.black);
	g.drawLine (0, field_height, screen_width, field_height);
	
	String s = "" + lives;

	int w = big_font_metrics.stringWidth (s);
	
	g.setColor (Color.black);
	g.drawString (s,
		      screen_width - w,
		      field_height + big_font_metrics.getHeight ());

	g.drawImage (lives > 0 ? player_image : dead_player_image,
		     screen_width - w - 32,
		     field_height, this);
    }
    
    /* Paint the screen. */
    public void paint (Graphics g)
    {
	if (need_reset)
	{
	    reset_game ();
	    need_reset = false;
	}

	if (clear_screen)
	{
	    g.clearRect (0, 0, screen_width, field_height + stats_height);
	    clear_screen = false;
	}
	
	if (paused)
	{
	    paint_paused (g);
	    return;
	}
	
	if (update_mushrooms)
	{
	    paint_mushrooms (g);
	    update_mushrooms = false;
	}

	paint_shots (g);
	paint_centipede (g);
	paint_beetles (g);
	paint_scorpions (g);
	paint_fleas (g);
	paint_spiders (g);
	paint_player (g);

	if (update_score || update_level)
	{
	    paint_score_and_level (g);
	    update_score = update_level = false;
	}
	
	if (update_lives)
	{
	    paint_lives (g);
	    update_lives = false;
	}
	
	if (expand_mushrooms)
	{
	    paint_mushrooms_expand (g);
	    expand_mushrooms = false;
	}

	if (game_over)
	{
	    paint_game_over (g);
	}
    }
}

/*
Local variables:
eval: (progn (make-local-variable 'compile-command) (setq compile-command (concat "javac " buffer-file-name)))
End:

*/