# Activity controller
class ActivitiesController < ApplicationController
  before_action :authenticate_user!

  def index
    @activities = Activity.all
  end

  def new
    @activity = Activity.new
  end

  def create
    @activity = Activity.new(activity_params)
    if @activity.save
      flash[:success] = 'Actividad creado con éxito'
      redirect_to activities_path
    else
      render 'new'
    end
  end

  def edit
    @activity = Activity.find(params[:id])
  end

  def update
    @activity = Activity.find(params[:id])
    if @activity.update_attributes(activity_params)
      flash[:success] = 'Activity updated successfully'
      redirect_to activities_path
    else
      render 'edit'
    end
  end

  private

  def activity_params
    params.require(:activity).permit(:name, :description, :hours)
  end
end
