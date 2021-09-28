package com.hugomage.aquafina.client.model;


import com.google.common.collect.ImmutableList;
import com.hugomage.aquafina.entity.GoblinSharkEntity;
import com.hugomage.aquafina.entity.GuitarSharkEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GuitarSharkSharkModel<T extends GuitarSharkEntity> extends EntityModel<T>  {
    private final ModelRenderer fishe;
    private final ModelRenderer body_root;
    private final ModelRenderer body;
    private final ModelRenderer cube_r1;
    private final ModelRenderer right_fin;
    private final ModelRenderer left_fin;
    private final ModelRenderer tail;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
	public GuitarSharkSharkModel() {
        texWidth = 64;
        texHeight = 64;

        fishe = new ModelRenderer(this);
        fishe.setPos(0.0F, 23.0F, 4.0F);


        body_root = new ModelRenderer(this);
        body_root.setPos(0.0F, 0.0F, 1.0F);
        fishe.addChild(body_root);


        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, -5.0F);
        body_root.addChild(body);
        body.texOffs(25, 0).addBox(-3.0F, 0.0F, -8.0F, 5.0F, 1.0F, 3.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-4.0F, -1.0F, -5.0F, 7.0F, 2.0F, 10.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.0F, -1.0F, 2.0F);
        body.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.0873F, 0.0F, 0.0F);
        cube_r1.texOffs(18, 13).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        right_fin = new ModelRenderer(this);
        right_fin.setPos(-4.0F, 0.0F, -5.0F);
        body_root.addChild(right_fin);
        right_fin.texOffs(18, 13).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

        left_fin = new ModelRenderer(this);
        left_fin.setPos(3.0F, 0.0F, -5.0F);
        body_root.addChild(left_fin);
        left_fin.texOffs(21, 23).addBox(0.0F, 0.0F, -3.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setPos(0.0F, 0.0F, 1.0F);
        fishe.addChild(tail);
        tail.texOffs(25, 5).addBox(-4.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        tail.texOffs(0, 27).addBox(1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        tail.texOffs(0, 20).addBox(1.0F, 1.0F, 6.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
        tail.texOffs(0, 13).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 2.0F, 11.0F, 0.0F, false);
        tail.texOffs(0, 6).addBox(-4.0F, 1.0F, 6.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(0.0F, 0.0F, 11.0F);
        tail.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.1309F, 0.0F, 0.0F);
        cube_r2.texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(0.0F, 0.0F, 11.0F);
        tail.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.1745F, 0.0F, 0.0F);
        cube_r3.texOffs(0, 13).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(0.0F, -1.0F, 0.0F);
        tail.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.1745F, 0.0F, 0.0F);
        cube_r4.texOffs(11, 27).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.tail.yRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.left_fin.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -1.8F * f1;
        this.right_fin.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 1.8F * f1;
    }


    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.fishe).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}