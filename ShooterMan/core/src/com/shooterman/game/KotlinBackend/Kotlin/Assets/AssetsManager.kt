package com.shooterman.game.KotlinBackend.Kotlin.Assets
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas


class AssetsManager private constructor(){
    companion object Manager {
        val manager: AssetManager = AssetManager()
        fun load()
        {
            manager.load("Parallax/layer-1.png", Texture::class.java)
            manager.load("Parallax/layer-2.png", Texture::class.java)
            manager.load("Parallax/layer-3.png", Texture::class.java)
            manager.load("Parallax/layer-4.png", Texture::class.java)
            manager.load("Atlases/buttonatlas/buttons.png",Texture::class.java)
            manager.load("Atlases/buttonatlas/buttons.atlas",TextureAtlas::class.java)
            manager.load("Atlases/buttonatlas/hand.png",Texture::class.java)
            manager.load("Atlases/buttonatlas/hand.atlas",TextureAtlas::class.java)
            manager.load("Atlases/buttonatlas/pauseButton.png",Texture::class.java)
            manager.load("Atlases/buttonatlas/pauseButton.atlas",TextureAtlas::class.java)

            manager.load("Atlases/enemyAnimation/dragon.png",Texture::class.java)
            manager.load("Atlases/enemyAnimation/dragon.atlas",TextureAtlas::class.java)
            manager.load("Atlases/enemyAnimation/dragonboss.png",Texture::class.java)
            manager.load("Atlases/enemyAnimation/dragonboss.atlas",TextureAtlas::class.java)
            manager.load("Atlases/enemyAnimation/fireball.png",Texture::class.java)
            manager.load("Atlases/enemyAnimation/fireball.atlas",TextureAtlas::class.java)
            manager.load("Atlases/enemyAnimation/fleder.png",Texture::class.java)
            manager.load("Atlases/enemyAnimation/fleder.atlas",TextureAtlas::class.java)

            manager.load("Atlases/enemyShots/fireballlow.png",Texture::class.java)
            manager.load("Atlases/enemyShots/fireballlow.atlas",TextureAtlas::class.java)
            manager.load("Atlases/obstacleAnim/coinAnim.png",Texture::class.java)
            manager.load("Atlases/obstacleAnim/coinAnim.atlas",TextureAtlas::class.java)

            manager.load("Atlases/Playeranim/player.png",Texture::class.java)
            manager.load("Atlases/Playeranim/player.atlas",TextureAtlas::class.java)
            manager.load("Atlases/Playeranim/playerhit.png",Texture::class.java)

            manager.load("enviroment/fire1.png",Texture::class.java)
            manager.load("enviroment/heart.png",Texture::class.java)
            manager.load("enviroment/hitRect.png",Texture::class.java)
            manager.load("enviroment/pauseblack.png",Texture::class.java)
            manager.load("enviroment/toggle.png",Texture::class.java)
            manager.load("enviroment/togglebackground.png",Texture::class.java)

            manager.load("Fonts/scorefont.fnt",BitmapFont::class.java)
            manager.load("Fonts/scorefont.png",Texture::class.java)

            manager.load("Music/gameover.wav", Music::class.java)
          //  manager.load("Music/hitSound.wav", Music::class.java)
            manager.load("Music/inGameMusic.mp3", Music::class.java)

        }
    }

}