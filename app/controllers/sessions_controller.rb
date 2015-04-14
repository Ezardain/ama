# Session controller
class SessionsController < ApplicationController
  before_action :authenticate_user!

  def index
    @sessions = Session.all
  end

  def new
    @session = Session.new
  end

  def create
    @session = User.new(session_params)
    if @session.save
      flash[:success] = 'Sesión creada con éxito'
      redirect_to users_admin_index_path
    else
      render 'new'
    end
  end

  private

  def session_params
    params.require(:session).permit(:activity_id, :name, :description, :type)
  end
end
