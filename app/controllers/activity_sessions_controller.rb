# Session controller
class ActivitySessionsController < ApplicationController
  before_action :authenticate_user!

  def index
    @activity_sessions = ActivitySession.all
  end

  def new
    @activity_session = ActivitySession.new
  end

  def create
    @activity_session = User.new(activity_session_params)
    if @activity_session.save
      flash[:success] = 'Sesión creada con éxito'
      redirect_to activity_sessions_path
    else
      render 'new'
    end
  end

  private

  def activity_session_params
    params.require(:activity_session).permit(:activity_id, :name, :description, :type)
  end
end
