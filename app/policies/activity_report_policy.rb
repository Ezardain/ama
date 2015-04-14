# User auth policy
class ActivityReportPolicy < ApplicationPolicy
  def index?
    user.admin?
  end

  def new?
    user.user? || user.admin?
  end

  def create?
    user.user? || user.admin?
  end

  def edit?
    user.admin?
  end

  def update?
    user.admin?
  end

  def destroy?
    user.admin?
  end
end