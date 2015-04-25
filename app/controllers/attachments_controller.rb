# Attachments controller
class AttachmentsController < ApplicationController
  before_action :authenticate_user!

  def index
    @attachments = Attachment.all
    authorize Attachment
  end

  def new
    @attachment = Attachment.new
    authorize @attachment
  end

  def create
    @attachment = Attachment.new(attachment_params)
    authorize @attachment
    if @attachment.save
      flash[:notice] = 'Actividad creado con éxito'
      redirect_to activity_path(@attachment.activity)
    else
      render 'new'
    end
  end

  def edit
    @attachment = Attachment.find(params[:id])
    authorize @attachment
  end

  def update
    @attachment = Attachment.find(params[:id])
    authorize @attachment
    if @attachment.update_attributes(attachment_params)
      flash[:success] = 'Attachment updated successfully'
      redirect_to attachments_path
    else
      render 'edit'
    end
  end

  private

  def attachment_params
    params.require(:attachment).permit(:activity_id, :file, :name)
  end
end
