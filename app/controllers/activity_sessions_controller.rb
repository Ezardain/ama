# Session controller
class ActivitySessionsController < ApplicationController
  before_action :authenticate_user!

  def index
    @activity_sessions = ActivitySession.all
    authorize ActivitySession
  end

  def new
    @activity_session = ActivitySession.new
    authorize @activity_session
  end

  def create
    @activity_session = ActivitySession.new(activity_session_params)
    authorize @activity_session
    if @activity_session.save
      flash[:success] = 'Sesión creada con éxito'
      redirect_to activity_sessions_path
    else
      render 'new'
    end
  end

  def edit
    @activity_session = ActivitySession.find(params[:id])
    authorize @activity_session
  end

  def update
    @activity_session = ActivitySession.find(params[:id])
    authorize @activity_session
    if @activity_session.update_attributes(activity_session_params)
      flash[:success] = 'Sesion updated successfully'
      redirect_to activity_sessions_path
    else
      render 'edit'
    end
  end

  private

  def activity_session_params
    params.require(:activity_session).permit(:activity_id, :activity_instance_id, :name, :description, :type)
  end
end
